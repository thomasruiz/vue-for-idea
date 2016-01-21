package io.j99.idea.vue.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import io.j99.idea.vue.psi.VueCompositeElement;
import org.jetbrains.annotations.NotNull;

/**
 * Created by android on 1/14/16.
 */
public class VueCompositeElementImpl extends ASTWrapperPsiElement implements VueCompositeElement {
    public VueCompositeElementImpl(@NotNull ASTNode astNode) {
        super(astNode);
    }
}
