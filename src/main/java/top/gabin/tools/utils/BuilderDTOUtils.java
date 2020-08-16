package top.gabin.tools.utils;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuilderDTOUtils {
    @Data
    static class DTO {
        private boolean isObject;
        private String name;
        private String field;
        private String type;
        private String required;
        private String desc;
        private List<DTO> childList = new ArrayList<>();

        public DTO(boolean isObject, String name, String field, String type, String required, String desc) {
            this.isObject = isObject;
            this.name = name;
            this.field = field;
            this.type = type;
            this.required = required;
            this.desc = desc;
        }
    }

    public void builder(String url, String path, String fileName) throws IOException {
        String[] modules = url.split("/wxpay/")[1].split("chapter")[0].split("/");
        Document document = Jsoup.connect(url).get();
        // 单页文档中有多个接口
        boolean mulEntity = document.select(".part").size() > 4;
        int[] is = new int[]{-1, -1};
        Elements tables = document.select("table");
        tables.forEach(table -> {
            List<DTO> list = getDtos(table);
            boolean containsPathParams = containsPathParams(list);
            String text = table.parent().parent().select("h3").text();
            boolean request = isRequest(text);
            boolean response = isResponse(text);
            if (!request && !response) {
                return;
            }
            String newFileName = fileName + (request ? "Request" : "Response");
            if (mulEntity && is[request ? 0 : 1]++ >= 0) {
                newFileName += is[request ? 0 : 1];
            }
            try {
                String module = String.join("/", modules);
                String sourcePath = path + (request ? "request/" : "response/") + module;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("package " + sourcePath.replaceAll("src/main/java/", "").replaceAll("/", ".") + ";\n\n");

                if (containsPathParams) {
                    stringBuilder.append("import com.fasterxml.jackson.annotation.JsonIgnore;\n");
                }
                stringBuilder.append("import lombok.Data;\n" +
                        "import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n" +
                        "import com.fasterxml.jackson.annotation.JsonProperty;\n\n");

                if (response) {
                    stringBuilder.append("import top.gabin.tools.response.AbstractResponse;\n\n");
                }
                List<DTO> tempList = list;
                outer:
                while (!tempList.isEmpty()) {
                    List<DTO> temp = new ArrayList<>();
                    for (DTO dto : tempList) {
                        if (dto.getType().equals("array")) {
                            stringBuilder.append("import java.util.List;\n\n");
                            break outer;
                        }
                        List<DTO> childList = dto.getChildList();
                        if (!childList.isEmpty()) {
                            temp.addAll(childList);
                        }
                    }
                    tempList = temp;
                }
                stringBuilder.append("\n");

                String classFunc = table.parent().parent().parent().select(".overview p").eq(1).text();
                stringBuilder.append("/**\n" +
                        " * <pre>\n" +
                        " * " + classFunc + "\n");
                stringBuilder.append(String.format(" * 文档地址:%s\n", url));
                if (response) {
                    tables.stream().filter(element -> element.select("tbody tr").eq(0).select("td").size() == 4).findFirst().ifPresent(codeTable -> {
                        stringBuilder.append(String.format(" * %s\t%s\t%s\t%s\n", "状态码", "错误码", "描述", "解决方案"));
                        codeTable.select("tbody tr").forEach(tr -> {
                            Elements tds = tr.select("td");
                            stringBuilder.append(String.format(" * %s\t%s\t%s\t%s\n",
                                    getText(tds, 0),
                                    getText(tds, 1),
                                    getText(tds, 2),
                                    getText(tds, 3)
                            ));
                        });
                    });
                }
                stringBuilder.append(" * </pre>\n */\n");
                String ignoreFields = getIgnoreFields(list);
                stringBuilder.append("@Data\n" +
                        "@JsonIgnoreProperties(" + ignoreFields + ")\n" +
                        "public class " + newFileName + (response ? " extends AbstractResponse" : "") + " {");
                stringBuilder.append("\n");
                for (DTO dto : list) {
                    if (dto == null) {
                        continue;
                    }
                    String content = "\t/**\n\t * <pre>\n\t * 字段名：%s\n\t * 变量名：%s\n\t * 是否必填：%s\n\t * 类型：%s\n\t * 描述：%s \n\t * </pre>\n\t */\n";
                    String field = dto.getField();
                    content = String.format(content, dto.getName(), field, dto.getRequired(), dto.getType(), dto.getDesc());
                    stringBuilder.append(content);
                    if (isPathParams(dto)) {
                        stringBuilder.append("\t@JsonIgnore\n");
                    }
                    stringBuilder.append(String.format("\t@JsonProperty(value = \"%s\")\n", field));
                    stringBuilder.append("\tprivate " + getType(dto) + " " + getField(field) + ";\n\n");
                }
                List<DTO> objectDTOList = new ArrayList<>();
                for (DTO dto : list) {
                    if (dto == null) {
                        continue;
                    }
                    List<DTO> childList = dto.getChildList();
                    if (!childList.isEmpty()) {
                        objectDTOList.add(dto);
                    }
                }
                while (!objectDTOList.isEmpty()) {
                    objectDTOList = buildObject(stringBuilder, objectDTOList);
                }
                stringBuilder.append("}");
                Path file = Paths.get(sourcePath);
                if (!Files.exists(file)) {
                    Files.createDirectory(file);
                }
                Files.write(file.resolve(newFileName + ".java"), stringBuilder.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private String getIgnoreFields(List<DTO> list) {
        Stream<String> stringStream = list.stream()
                .filter(this::isPathParams)
                .map(dto -> "\"" + getField(dto.getField()) + "\"");
        String collect = stringStream.collect(Collectors.joining(", "));
        if (collect.split(",").length > 1) {
            return "{" + collect +  "}";
        }
        return collect;
    }

    private boolean containsPathParams(List<DTO> list) {
        return list.stream().anyMatch(this::isPathParams);
    }

    private boolean isPathParams(DTO dto) {
        return dto.getDesc().contains("path");
    }

    private boolean isResponse(String text) {
        switch (text) {
            case "返回参数":
            case "通知应答":
                return true;
            default:
                return false;
        }
    }

    private boolean isRequest(String text) {
        switch (text) {
            case "请求参数":
            case "通知参数":
            case "支付成功通知参数":
                return true;
            default:
                return false;
        }
    }

    private List<DTO> buildObject(StringBuilder stringBuilder, List<DTO> objectDTOList) throws IOException {
        List<DTO> childDTOList = new ArrayList<>();
        for (DTO parentDTO : objectDTOList) {
            String uppercaseField = getTopUppercaseField(parentDTO.getField());
            List<DTO> childList = parentDTO.getChildList();
            String ignoreFields = getIgnoreFields(childList);
            stringBuilder.append("\t@Data\n\t@JsonIgnoreProperties(" + ignoreFields + ")\n\tpublic static class " + uppercaseField + " {\n");
            for (DTO dto : childList) {
                String content = "\t\t/**\n\t\t * <pre>\n\t\t * 字段名：%s\n\t\t * 变量名：%s\n\t\t * 是否必填：%s\n\t\t * 类型：%s\n\t\t * 描述：%s \n\t\t * </pre>\n\t\t */\n";
                String field = dto.getField();
                content = String.format(content, dto.getName(), field, dto.getRequired(), dto.getType(), dto.getDesc());
                stringBuilder.append(content);
                if (isPathParams(dto)) {
                    stringBuilder.append("\t\t@JsonIgnore\n");
                }
                stringBuilder.append(String.format("\t\t@JsonProperty(value = \"%s\")\n", field));
                stringBuilder.append("\t\tprivate " + getType(dto) + " " + getField(field) + ";\n\n");
            }
            for (DTO dto : childList) {
                List<DTO> childList1 = dto.getChildList();
                if (!childList1.isEmpty()) {
                    childDTOList.add(dto);
                }
            }
            stringBuilder.append("\t}\n\n");
        }
        return childDTOList;
    }

    private List<DTO> getDtos(Element table) {
        Elements trs = table.select("tbody tr").eq(0).first().parent().children();
        if (trs.size() == 1 && trs.eq(0).select("td").size() != 5) {
            return Collections.emptyList();
        }
        return trs.stream().map(tr -> {
            Elements tds = tr.select("td");
            if (tds.eq(0).hasClass("object-sub")) {
                return null;
            }
            Elements descElement = tds.eq(4);
            String html = descElement.html();
            descElement.html(html.replaceAll("<br>", "@换行@"));
            DTO dto = new DTO(tr.hasClass("object"), getText(tds, 0), getText(tds, 1), getText(tds, 2), getText(tds, 3), getText(tds, 4));
            List<DTO> childList = getChildDtos(tr);
            dto.getChildList().addAll(childList);
            return dto;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private List<DTO> getChildDtos(Element sourceTr) {
        if (!sourceTr.hasClass("object")) {
            return Collections.emptyList();
        }
        Element object = sourceTr.nextElementSibling();

        Elements objectTrs = object.select("table tbody tr").eq(0).first().parent().children();
        if (objectTrs.size() == 1 && objectTrs.eq(0).select("td").size() != 5) {
            return Collections.emptyList();
        }
        return objectTrs.stream().map(tr -> {
            Elements tds = tr.select("td");
            if (tds.eq(0).hasClass("object-sub")) {
                return null;
            }
            Elements objectTds = tr.select("td");
            Elements objDescElement = objectTds.eq(4);
            String objHtml = objDescElement.html();
            objDescElement.html(objHtml.replaceAll("<br>", "@换行@"));
            DTO dto = new DTO(tr.hasClass("object"), getText(objectTds, 0), getText(objectTds, 1), getText(objectTds, 2), getText(objectTds, 3), get2Text(objectTds));
            if (dto.getField().isEmpty()) {
                return null;
            }
            List<DTO> childDtos = getChildDtos(tr);
            dto.getChildList().addAll(childDtos);
            return dto;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private String getType(DTO dto) {
        String type = dto.getType();
        type = type.toLowerCase();
        switch (type) {
            case "object":
                return getTopUppercaseField(dto.getField());
            case "array":
                return String.format("List<%s>", dto.isObject ? getTopUppercaseField(dto.getField()) : "String");
            case "bool":
            case "boolean":
                return "Boolean";
            default:
                if (type.startsWith("string")) {
                    return "String";
                }
                if (type.startsWith("int")) {
                    return "Integer";
                }
                return "String";
        }
    }

    private String getField(String field) {
        if (field.contains("_")) {
            String[] arr = field.split("_");
            StringBuilder sb = new StringBuilder(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                String str = arr[i];
                sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
            }
            return sb.toString();
        }
        return field;
    }

    private String getTopUppercaseField(String field) {
        if (field.contains("_")) {
            String[] arr = field.split("_");
            StringBuilder sb = new StringBuilder();
            for (String str : arr) {
                sb.append(str.substring(0, 1).toUpperCase()).append(str.substring(1));
            }
            return sb.toString();
        }
        return field.substring(0, 1).toUpperCase() + field.substring(1);
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

    private String get2Text(Elements tds) {
        String regex = "@换行@";
        String text = tds.eq(4).text();
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
