package io.j99.idea.vue;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import io.j99.idea.vue.highlighter.VueSyntaxHighligher;
import org.jetbrains.annotations.NotNull;

/**
 * Created by android on 1/14/16.
 */
public class VueLanguage extends Language {
    public VueLanguage(){
        super("Vue","text/vue");
        SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new SingleLazyInstanceSyntaxHighlighterFactory() {
            @NotNull
            @Override
            protected SyntaxHighlighter createHighlighter() {
                return new VueSyntaxHighligher();
            }
        });
    }
}
