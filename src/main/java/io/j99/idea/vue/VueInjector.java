package io.j99.idea.vue;

import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import io.j99.idea.vue.psi.impl.VueBodyCodeImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static com.intellij.patterns.PlatformPatterns.psiElement;
/**
 * Created by apple on 16/1/21.
 */
public class VueInjector implements MultiHostInjector {
    private static final PsiElementPattern.Capture<VueBodyCodeImpl> JS_ELEMENT_PATTERN =
            psiElement(VueBodyCodeImpl.class);
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement host) {
        if(JS_ELEMENT_PATTERN.accepts(host)){
            String language=((VueBodyCodeImpl)host).getLang();
        }
    }

    @NotNull
    @Override
    public List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return Arrays.asList(VueBodyCodeImpl.class);
    }
}
