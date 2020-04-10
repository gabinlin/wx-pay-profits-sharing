package top.gabin.tools.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuilderDTOUtils {
    public static class DTO {
        private String name;
        private String field;
        private String type;
        private String required;
        private String desc;
        private List<DTO> childList = new ArrayList<>();

        public DTO(String name, String field, String type, String required, String desc) {
            this.name = name;
            this.field = field;
            this.type = type;
            this.required = required;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRequired() {
            return required;
        }

        public void setRequired(String required) {
            this.required = required;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<DTO> getChildList() {
            return childList;
        }

        public void setChildList(List<DTO> childList) {
            this.childList = childList;
        }
    }

    public void builder(String url, String path, String fileName) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements tables = document.select("table");
        tables.forEach(table -> {
            List<DTO> list = getDtos(table);

            boolean request = table.parent().parent().text().contains("请求");
            boolean response = table.parent().parent().text().contains("返回参数");
            if (!request && !response) {
                return;
            }
            String newFileName = fileName + (request ? "Request" : "Response");
            try {
                String sourcePath = path + (request ? "request" : "response");
                FileWriter fileWriter = new FileWriter(sourcePath + "/" + newFileName + ".java");
                fileWriter.write("package " + sourcePath.replaceAll("src/main/java/", "").replaceAll("/", ".") + ";\n\n");

                fileWriter.write("import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n" +
                        "import com.fasterxml.jackson.annotation.JsonProperty;\n\n");
                String classFunc = table.parent().parent().parent().select(".overview p").eq(1).text();
                fileWriter.write("/**\n" +
                        " * <pre>\n" +
                        " * " + classFunc + "\n" +
                        " * </pre>\n" +
                        " */\n");
                fileWriter.write("@JsonIgnoreProperties(ignoreUnknown = true)\npublic class " + newFileName + " {");
                fileWriter.write("\n");
                for (DTO dto : list) {
                    if (dto == null) {
                        continue;
                    }
                    String content = "\t/**\n\t * <pre>\n\t * 字段名：%s\n\t * 变量名：%s\n\t * 是否必填：%s\n\t * 类型：%s\n\t * 描述：%s \n\t * </pre>\n\t */\n";
                    String field = dto.getField();
                    content = String.format(content, dto.getName(), field, dto.getRequired(), dto.getType(), dto.getDesc());
                    fileWriter.write(content);
                    fileWriter.write(String.format("\t@JsonProperty(value = \"%s\")\n", field));
                    fileWriter.write("\tprivate " + getType(dto) + " " + getField(field) + ";\n\n");
                }
                List<DTO> objectDTOList = new ArrayList<>();
                for (DTO dto : list) {
                    if (dto == null) {
                        continue;
                    }
                    String sourceField = dto.getField();
                    String field = getTopUppercaseField(sourceField);
                    String param = getField(sourceField);
                    String getString = String.format("\tpublic %s get%s() {\n\t\treturn this.%s;\n\t}\n\n", getType(dto), field, param);
                    String setString = String.format("\tpublic void set%s(%s %s) {\n\t\tthis.%s = %s;\n\t}\n\n", field, getType(dto), param, param, param);
                    fileWriter.write(getString);
                    fileWriter.write(setString);
                    List<DTO> childList = dto.getChildList();
                    if (!childList.isEmpty()) {
                        objectDTOList.add(dto);
                    }
                }

                for (DTO parentDTO : objectDTOList) {
                    String uppercaseField = getTopUppercaseField(parentDTO.getField());
                    fileWriter.write("\t@JsonIgnoreProperties(ignoreUnknown = true)\n\tpublic static class " + uppercaseField + " {\n");

                    List<DTO> childList = parentDTO.getChildList();
                    for (DTO dto : childList) {
                        String content = "\t\t/**\n\t\t * <pre>\n\t\t * 字段名：%s\n\t\t * 变量名：%s\n\t\t * 是否必填：%s\n\t\t * 类型：%s\n\t\t * 描述：%s \n\t\t * </pre>\n\t\t */\n";
                        String field = dto.getField();
                        content = String.format(content, dto.getName(), field, dto.getRequired(), dto.getType(), dto.getDesc());
                        fileWriter.write(content);
                        fileWriter.write(String.format("\t\t@JsonProperty(value = \"%s\")\n", field));
                        fileWriter.write("\t\tprivate String " +getField(field) + ";\n\n");
                    }
                    for (DTO dto : childList) {
                        String sourceField = dto.getField();
                        String field = getTopUppercaseField(sourceField);
                        String param = getField(sourceField);
                        String getString = String.format("\t\tpublic %s get%s() {\n\t\t\treturn this.%s;\n\t\t}\n\n", getType(dto), field, param);
                        String setString = String.format("\t\tpublic void set%s(%s %s) {\n\t\t\tthis.%s = %s;\n\t\t}\n\n", field, getType(dto), param, param, param);
                        fileWriter.write(getString);
                        fileWriter.write(setString);
                    }
                    fileWriter.write("\t}\n\n");
                }

                fileWriter.write("}");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private List<DTO> getDtos(Element table) {
        Elements trs = table.select("tbody tr");
        return trs.stream().map(tr -> {
            if (tr.parent().parent().parent().hasClass("object-sub")) {
                return null;
            }
            Elements tds = tr.select("td");
            if (tds.eq(0).hasClass("object-sub")) {
                return null;
            }
            Elements descElement = tds.eq(4);
            String html = descElement.html();
            descElement.html(html.replaceAll("<br>", "@换行@"));
            DTO dto = new DTO(getText(tds, 0), getText(tds, 1), getText(tds, 2), getText(tds, 3), getText(tds, 4));
            if (tr.hasClass("object")) {
                Element object = tr.nextElementSibling();
                Elements objectTrs = object.select("table tbody tr");
                List<DTO> childList = objectTrs.stream().map(objectTr -> {
                    Elements objectTds = objectTr.select("td");
                    Elements objDescElement = objectTds.eq(4);
                    String objHtml = objDescElement.html();
                    objDescElement.html(objHtml.replaceAll("<br>", "@换行@"));
                    return new DTO(getText(objectTds, 0), getText(objectTds, 1), getText(objectTds, 2), getText(objectTds, 3), get2Text(objectTds, 4));
                }).collect(Collectors.toList());
                dto.getChildList().addAll(childList);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    private String getType(DTO dto) {
        String type = dto.getType();
        switch (type) {
            case "object":
                return getTopUppercaseField(dto.getField());
            default:
                return "String";
        }
    }

    private String getField(String field) {
        if (field.contains("_")) {
            String[] arr = field.split("_");
            StringBuilder sb = new StringBuilder(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                String str = arr[i];
                sb.append(str.substring(0, 1).toUpperCase() + str.substring(1));
            }
            return sb.toString();
        }
        return field;
    }

    private String getTopUppercaseField(String field) {
        if (field.contains("_")) {
            String[] arr = field.split("_");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                String str = arr[i];
                sb.append(str.substring(0, 1).toUpperCase() + str.substring(1));
            }
            return sb.toString();
        }
        return field;
    }

    private String getText(Elements tds, int i) {
        String regex = "@换行@";
        String text = tds.eq(i).text();
        if (text.startsWith("query")) {
            text = text.substring(5);
        }
        if (text.contains(regex)) {
            return "\n\t *  " + text.replaceAll(regex, "\n\t * ");
        } else {
            return text;
        }
    }

    private String get2Text(Elements tds, int i) {
        String regex = "@换行@";
        String text = tds.eq(i).text();
        if (text.startsWith("query")) {
            text = text.substring(5);
        }
        if (text.contains(regex)) {
            return "\n\t\t *  " + text.replaceAll(regex, "\n\t\t * ");
        } else {
            return text;
        }
    }
}
