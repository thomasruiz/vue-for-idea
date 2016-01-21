package io.j99.idea.vue

import com.intellij.lang.ASTNode
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import io.j99.idea.vue.psi.VueFile

/**
 * Created by android on 15-12-25.
 */
class VueParserDefinition : com.intellij.lang.xhtml.XHTMLParserDefinition() {
    override fun createFile(fileViewProvider: FileViewProvider): PsiFile {
        return VueFile(fileViewProvider)
    }

    override fun createLexer(p0: Project?): Lexer {
//        return FlexAdapter(_VueLexter())
        return super.createLexer(p0)
    }

    override fun createElement(p0: ASTNode?): PsiElement {
        return super.createElement(p0)
//        return VueTypes.Factory.createElement(p0);
    }
}
