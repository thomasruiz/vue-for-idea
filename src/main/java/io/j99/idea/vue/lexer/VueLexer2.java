package io.j99.idea.vue.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.psi.tree.IElementType;
import io.j99.idea.vue.Tokenizer;

/**
 * Created by android on 1/14/16.
 */
public class VueLexer2 extends Lexer{
    private final Tokenizer tokenizer;
    public CharSequence buffer = null;
    public int index = 0;
    public int startOffset = 0;
    public int endOffset = 0;
    public VueLexer2(Tokenizer tokenizer){
        this.tokenizer=tokenizer;
    }
    @Override
    public void start(CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.buffer = buffer;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.index = initialState;
        tokenizer.resetSequence(buffer.subSequence(startOffset, endOffset));
    }
    @Override
    public int getState() {
        return index;
    }

    @Override
    public IElementType getTokenType() {
        return tokenizer.getTokenType(index);
    }

    @Override
    public int getTokenStart() {
        return tokenizer.getTokenStart(index);
    }

    @Override
    public int getTokenEnd() {
        return tokenizer.getTokenEnd(index);
    }

    @Override
    public void advance() {
        index++;
    }

    @Override
    public LexerPosition getCurrentPosition() {
        return new CustomLexerPosition(getTokenStart(), index);
    }

    @Override
    public void restore(LexerPosition position) {
        index = position.getState();
    }

    @Override
    public CharSequence getBufferSequence() {
        return buffer;
    }

    @Override
    public int getBufferEnd() {
        return 0;
    }

    class CustomLexerPosition implements LexerPosition {
        public int offset;
        public int state;

        public CustomLexerPosition(final int offset, final int state) {
            this.offset = offset;
            this.state = state;
        }

        public int getOffset() {
            return offset;
        }

        public int getState() {
            return state;
        }
    }
}
