package com.rolfie.webdetector.ui.component.response.json;

public class JsonResponse {

    public static String getJson() {
        StringBuilder json = new StringBuilder();
        json.append("{")
                .append("\"alts\": {")
                .append("\"16\" , \"src=\"/assets/logos/pictoaccess-eec50802bd8cc04dee8d1f0104fad0b5978c5c15e7a60bcb6445b3f24b30399c.png\" \"")
                .append("\"147\",\" src=\"https://www.pictoaccess.fr/assets/home/benefice/euro-585b3f865baadb4d5d31d0dec5a0f2e0d9f05254cbc3169a8f73502f0ca91cc7.png\" src=\"https://www.pictoaccess.fr/assets/home/benefice/euro-585b3f865baadb4d5d31d0dec5a0f2e0d9f05254cbc3169a8f73502f0ca91cc7.png\" \" ")
                .append("}");

        return json.toString();
    }
}
