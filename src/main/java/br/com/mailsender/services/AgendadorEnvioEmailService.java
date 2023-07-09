package br.com.mailsender.services;

import br.com.mailsender.dtos.NoticiasIbgeDto;
import br.com.mailsender.entities.Noticia;
import br.com.mailsender.util.AgendadorEnvioEmailUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendadorEnvioEmailService extends AgendadorEnvioEmailUtils {

    private final RestTemplate restTemplate;

    public NoticiasIbgeDto obterNoticiasIbge() {
        String dataConsulta = obterDataConsulta();
        String url = "https://servicodados.ibge.gov.br/api/v3/noticias/?de=" + dataConsulta;

        NoticiasIbgeDto noticiasIBGEDto = null;

        ResponseEntity<NoticiasIbgeDto> response = restTemplate.getForEntity(url, NoticiasIbgeDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            noticiasIBGEDto = response.getBody();

            if (noticiasIBGEDto != null && !noticiasIBGEDto.getItems().isEmpty()) {

                for (NoticiasIbgeDto.Item item : noticiasIBGEDto.getItems()) {
                    Gson gson = new Gson();
                    String json = item.getImagens();
                    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

                    String imageIntro = jsonObject.get("image_intro").getAsString();
                    item.setUrlImagem("https://agenciadenoticias.ibge.gov.br//" + imageIntro);
                }

            }

        }

        return noticiasIBGEDto;
    }

    public String criarCabecalho() {
        StringBuilder cabecalho = new StringBuilder();

        cabecalho
                .append("<div style=\"text-align: center; background-color:#9e0000;color:white;\">")
                .append("   <h1>Bom dia!</h1>")
                .append("</div>");

        return cabecalho.toString();
    }

    public String criarCabecalhoAniversariante() {
        StringBuilder cabecalho = new StringBuilder();

        cabecalho
                .append("<div style=\"text-align: center; background-color:#9e0000;color:white;\">")
                .append("   <h1>Bom dia! Feliz aniversário!").append("</h1>")
                .append("</div>");

        return cabecalho.toString();
    }

    public String criarCorpoEmail(List<NoticiasIbgeDto.Item> noticiasIbge, List<Noticia> noticias) {
        StringBuilder corpoEmail = new StringBuilder();

        for (NoticiasIbgeDto.Item noticiaIbge : noticiasIbge) {
            corpoEmail
                    .append("<div style=\"margin-left: 15px;\">")
                    .append("   <h2 style=\"text-align: left; margin-bottom:0\">")
                    .append("       <u>").append(noticiaIbge.getTitulo()).append("</u>").append("(IBGE)")
                    .append("   </h2>")
                    .append("   <br>")
                    .append("   <div style=\"display:inline-flex;max-height: 66.5px;\">")
                    .append("       <img")
                    .append("           style=\"padding-right:15px\"")
                    .append("           src=\"").append(noticiaIbge.getUrlImagem()).append("\"")
                    .append("           width=\"120px\" heigth=\"66.5px\">")
                    .append("       <div style=\"overflow: hidden;\">")
                    .append(noticiaIbge.getIntroducao())
                    .append("       </div>")
                    .append("   </div>")
                    .append("   <br>")
                    .append("   <div style=\"text-align: center;\">")
                    .append("       Para saber mais, <a href=\"").append(noticiaIbge.getLink()).append("\">clique aqui</a>")
                    .append("   </div>")
                    .append("   <hr>")
                    .append("</div>");
        }

        for (Noticia noticia : noticias) {
            corpoEmail
                    .append("<div style=\"margin-left: 15px;\">")
                    .append("   <h2 style=\"text-align: left; margin-bottom:0\">")
                    .append("       <u>").append(noticia.getTitulo()).append("</u>").append("(IBGE)")
                    .append("   </h2>")
                    .append("   <br>")
                    .append("   <div style=\"display:inline-flex;max-height: 66.5px;\">")
                    .append("       <img")
                    .append("           style=\"padding-right:15px\"")
                    .append("           src=\"").append(noticia.getUrlImagem()).append("\"")
                    .append("           width=\"120px\" heigth=\"66.5px\">")
                    .append("       <div style=\"overflow: hidden;\">")
                    .append(noticia.getDescricao())
                    .append("       </div>")
                    .append("   </div>")
                    .append("   <br>")
                    .append("   <div style=\"text-align: center;\">")
                    .append("       Para saber mais, <a href=\"").append(noticia.getLink()).append("\">clique aqui</a>")
                    .append("   </div>")
                    .append("   <hr>")
                    .append("</div>");
        }

        return corpoEmail.toString();
    }

    public String criarConteudoEmail(String cabecalho, String corpoEmail) {
        StringBuilder conteudoEmail = new StringBuilder();

        conteudoEmail
                .append("<html lang=\"pt-br\">")
                .append("   <head>")
                .append("       <meta charset=\"UTF-8\">")
                .append("   </head>")
                .append("   <body>")
                .append("       <div style=\"border: 1px solid lightgray; border-radius:15px; background-color:#ebebeb\">")
                .append(cabecalho)
                .append("       <br>")
                .append(corpoEmail)
                .append("       </div>")
                .append("   </body>")
                .append("</html>");

        return conteudoEmail.toString();
    }
}
