package io.j99.idea.vue;

import com.intellij.lang.Language;
import com.intellij.lang.css.CSSLanguage;
import com.intellij.lang.html.HTMLLanguage;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.lang.javascript.JavaScriptSupportLoader;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import io.j99.idea.vue.psi.VueCode;
import io.j99.idea.vue.psi.VueLang;
import io.j99.idea.vue.psi.VueTags;
import org.coffeescript.CoffeeScriptLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.less.LESSLanguage;
import org.jetbrains.plugins.sass.SASSLanguage;
import org.jetbrains.plugins.stylus.StylusLanguage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * Created by apple on 16/1/21.
 */
public class VueInjector implements MultiHostInjector {
    private static final PsiElementPattern.Capture<VueCode> JS_ELEMENT_PATTERN =
            psiElement(VueCode.class).withParent(psiElement(VueTags.class).withName("script"));
    private static final PsiElementPattern.Capture<VueCode> HTML_ELEMENT_PATTERN =
            psiElement(VueCode.class).withParent(psiElement(VueTags.class).withName("template"));
    private static final PsiElementPattern.Capture<VueCode> STYLE_ELEMENT_PATTERN =
            psiElement(VueCode.class).withParent(psiElement(VueTags.class).withName("style"));
    private static HashMap<String,Language> langs=new HashMap<>();
    static {
        langs.put("html",HTMLLanguage.INSTANCE);
        langs.put("css",CSSLanguage.INSTANCE);
        langs.put("styus", StylusLanguage.INSTANCE);
        langs.put("sass", SASSLanguage.INSTANCE);
        langs.put("less", LESSLanguage.INSTANCE);
        langs.put("js",JavaScriptSupportLoader.JAVASCRIPT.getLanguage());
        langs.put("babel",JavaScriptSupportLoader.ECMA_SCRIPT_6);
        langs.put("coffee", CoffeeScriptLanguage.INSTANCE);
    }
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement host) {
        VueCode codeNode = (VueCode) host;
        VueTags tag = (VueTags) codeNode.getParent();
        VueLang langNode = tag.getLang();
        String lang=null;
        if (HTML_ELEMENT_PATTERN.accepts(host)) {
            lang=langNode==null?"html":langNode.getText();
        }else if (JS_ELEMENT_PATTERN.accepts(host)) {
            lang=langNode==null?"js":langNode.getText();
        }else if (STYLE_ELEMENT_PATTERN.accepts(host)) {
            lang=langNode==null?"css":langNode.getText();
        }
        if(lang!=null) {
            injectCode(registrar, lang, codeNode);
        }
    }
    private void injectCode(MultiHostRegistrar registrar, String lang, VueCode code){
        Language language=langs.get(lang);
        registrar.startInjecting(language)
                .addPlace(null, null, code, TextRange.from(0, code.getTextLength() - 1))
                .doneInjecting();
    }
    @NotNull
    @Override
    public List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return Arrays.asList(VueCode.class);
    }
}
