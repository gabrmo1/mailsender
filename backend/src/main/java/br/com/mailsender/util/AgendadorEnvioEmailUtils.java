package br.com.mailsender.util;

import br.com.mailsender.entities.Noticia;
import br.com.mailsender.singleton.NoticiasDiariasSingleton;

import java.time.LocalDate;
import java.util.List;

public class AgendadorEnvioEmailUtils {

    protected String obterDataConsulta() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        int dia = yesterday.getDayOfMonth();
        int mes = yesterday.getMonthValue();
        int ano = yesterday.getYear();

        return mes + "-" + dia + "-" + ano;
    }

    protected String criarCabecalho() {

        return "<div style=\"text-align: center; background-color:#9e0000;color:white;\">" +
                "   <h1>Bom dia!</h1>" +
                "</div>";
    }

    protected String criarCabecalhoAniversariante() {

        return "<div style=\"text-align: center; background-color:#9e0000;color:white;\">" +
                "   <h1>Bom dia! Feliz aniversário!" + "</h1>" +
                "</div>";
    }

    protected String criarCorpoEmail(List<NoticiasDiariasSingleton.NoticiaDiaria> noticiasIbge, List<Noticia> noticias) {
        StringBuilder corpoEmail = new StringBuilder();

        for (NoticiasDiariasSingleton.NoticiaDiaria noticiaIbge : noticiasIbge) {
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
                    .append(noticiaIbge.getDescricao())
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
                    .append("       <u>").append(noticia.getTitulo()).append("</u>").append("(Newsletter)")
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

    protected String criarConteudoEmail(String cabecalho, String corpoEmail) {

        return "<html lang=\"pt-br\">" +
                "   <head>" +
                "       <meta charset=\"UTF-8\">" +
                "   </head>" +
                "   <body>" +
                "       <div style=\"border: 1px solid lightgray; border-radius:15px; background-color:#ebebeb\">" +
                cabecalho +
                "       <br>" +
                corpoEmail +
                "       </div>" +
                "   </body>" +
                "</html>";
    }

}
