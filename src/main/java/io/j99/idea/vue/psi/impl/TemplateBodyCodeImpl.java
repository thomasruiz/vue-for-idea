package io.j99.idea.vue.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.LiteralTextEscaper;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.impl.source.tree.injected.StringLiteralEscaper;
import io.j99.idea.vue.psi.TemplateBodyCode;
import io.j99.idea.vue.psi.VueElement;
import org.jetbrains.annotations.NotNull;

/**
 * Created by android on 1/14/16.
 */
public class TemplateBodyCodeImpl extends ASTWrapperPsiElement implements TemplateBodyCode,PsiLanguageInjectionHost {
    public TemplateBodyCodeImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }

    @Override
    public boolean isValidHost() {
        return false;
    }

    @Override
    public PsiLanguageInjectionHost updateText(@NotNull String text) {
        ASTNode valueNode = getNode().getFirstChildNode();
        ((LeafElement)valueNode).replaceWithText(text);
        return this;
    }

    @NotNull
    @Override
    public LiteralTextEscaper<? extends PsiLanguageInjectionHost> createLiteralTextEscaper() {
        return new StringLiteralEscaper(this);
    }
}
