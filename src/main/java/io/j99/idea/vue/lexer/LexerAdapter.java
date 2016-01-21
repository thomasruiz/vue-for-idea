package io.j99.idea.vue.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by apple on 16/1/21.
 */
public class LexerAdapter extends FlexAdapter {
    public LexerAdapter() {
        super(new VueLexer((Reader)null));
    }
}
