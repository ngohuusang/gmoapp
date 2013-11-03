package vn.gmostore.web.client.util.exceptiontranslators;

public interface Translator {
    Boolean isMatching();

    String getTranslatedMessage();
}
