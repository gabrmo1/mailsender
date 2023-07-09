package br.com.mailsender.util;

import java.time.LocalDate;

public class AgendadorEnvioEmailUtils {

    protected String obterDataConsulta() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        int dia = yesterday.getDayOfMonth();
        int mes = yesterday.getMonthValue();
        int ano = yesterday.getYear();

        return mes + "-" + dia + "-" + ano;
    }

}
