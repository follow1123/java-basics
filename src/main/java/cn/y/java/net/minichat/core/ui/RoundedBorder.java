package cn.y.java.net.minichat.core.ui;

/**
 * 圆角边框
 */
public class RoundedBorder implements Border {
    @Override
    public char[] getBorderCharacters() {
        return new char[]{ '─', '│', '─', '│', '╭', '╮', '╯', '╰' };
    }
}