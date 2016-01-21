package io.j99.idea.vue.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import io.j99.idea.vue.VueTypes;
import io.j99.idea.vue.psi.VueTagName;
import org.jetbrains.annotations.NotNull;

/**
 * Created by apple on 16/1/21.
 */
public abstract class VueCompositeHostElementImpl extends ASTWrapperPsiElement implements com.intellij.psi.PsiLanguageInjectionHost {
    public VueCompositeHostElementImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }
    public String getLang(){
        return "";
    }
    @Override
    public boolean isValidHost() {
//        return getCodeBlock()!=null;
        return true;
    }
    private PsiElement getCodeBlock(){
        PsiElement body = findChildByType(VueTypes.BODY_CODE);
        return body;
    }
    @Override
    public PsiLanguageInjectionHost updateText(@NotNull String s) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
        return LiteralTextEscaper.createSimple(this);
    }
}
