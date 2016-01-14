package io.j99.idea.vue.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import io.j99.idea.vue.VueFileType;
import io.j99.idea.vue.VueTokenizer;
import io.j99.idea.vue.lexer.VueLexer2;
import io.j99.idea.vue.lexer.VueTokenTypes;
import io.j99.idea.vue.psi.VueFile;
import io.j99.idea.vue.psi.impl.TemplateBodyCodeImpl;
import io.j99.idea.vue.psi.impl.VueElementImpl;
import org.jetbrains.annotations.NotNull;

/**
 * Created by android on 1/14/16.
 */
public class VueParserDefinition implements ParserDefinition {
    private static final IFileElementType FILE_ELEMENT_TYPE = new IFileElementType(VueFileType.INSTANCE.getLanguage());

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new VueLexer2(new VueTokenizer());
    }

    @Override
    public PsiParser createParser(Project project) {
        return new VueParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE_ELEMENT_TYPE;
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return TokenSet.create(VueTokenTypes.WHITE_SPACE);
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return  TokenSet.create();
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.create();
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        if(node.getElementType()==VueTokenTypes.TAG_SCRIPT_BODY){
            return new TemplateBodyCodeImpl(node);
        }
        return new VueElementImpl(node);
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new VueFile(fileViewProvider);
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode1) {
        return SpaceRequirements.MAY;
    }
}
