package io.j99.idea.vue.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import io.j99.idea.vue.VueFileType;
import io.j99.idea.vue.psi.VueElement;
import org.jetbrains.annotations.NotNull;

/**
 * Created by android on 1/14/16.
 */
public class VueElementImpl extends ASTWrapperPsiElement implements VueElement {
    private final ASTNode node;

    public VueElementImpl(@NotNull ASTNode astNode) {
        super(astNode);
        this.node=astNode;
    }

    @NotNull
    @Override
    public Language getLanguage() {
        return VueFileType.INSTANCE.getLanguage();
    }
}
