package io.j99.idea.vue;

import com.intellij.psi.tree.IElementType;

/**
 * Created by android on 1/14/16.
 */
public interface Tokenizer {
    int getTokenStart(int index);

    int getTokenEnd(int index);

    IElementType getTokenType(int index);

    void resetSequence(CharSequence buffer);
}
