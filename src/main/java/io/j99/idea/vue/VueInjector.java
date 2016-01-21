package io.j99.idea.vue;

import com.intellij.lang.css.CSSLanguage;
import com.intellij.lang.html.HTMLLanguage;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.lang.javascript.JavaScriptSupportLoader;
import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import io.j99.idea.vue.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import static com.intellij.patterns.PlatformPatterns.psiElement;
/**
 * Created by apple on 16/1/21.
 */
public class VueInjector implements MultiHostInjector {
    private static final PsiElementPattern.Capture<VueScriptBody> JS_ELEMENT_PATTERN =
            psiElement(VueScriptBody.class);
    private static final PsiElementPattern.Capture<VueTemplateBody> HTML_ELEMENT_PATTERN =
            psiElement(VueTemplateBody.class);
    private static final PsiElementPattern.Capture<VueStyleBody> STYLE_ELEMENT_PATTERN =
            psiElement(VueStyleBody.class);
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement host) {
        if(JS_ELEMENT_PATTERN.accepts(host)){
            registrar.startInjecting(JavaScriptSupportLoader.JAVASCRIPT.getLanguage())
                    .addPlace(null,null,(PsiLanguageInjectionHost)host,
                    TextRange.from(1,host.getTextLength()-2))
                    .doneInjecting();
        }
        if(HTML_ELEMENT_PATTERN.accepts(host)){
            registrar.startInjecting(HTMLLanguage.INSTANCE)
                    .addPlace(null,null,(PsiLanguageInjectionHost)host,
                            TextRange.from(1,host.getTextLength()-2))
                    .doneInjecting();
        }
        if(STYLE_ELEMENT_PATTERN.accepts(host)){
            registrar.startInjecting(CSSLanguage.INSTANCE)
                    .addPlace(null,null,(PsiLanguageInjectionHost)host,
                            TextRange.from(1,host.getTextLength()-2))
                    .doneInjecting();
        }
    }

    @NotNull
    @Override
    public List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return Arrays.asList(VueTemplateBody.class,VueStyleBody.class,VueScriptBody.class);
    }
}
