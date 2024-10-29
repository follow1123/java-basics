package cn.y.java.net.minichat.core.ui;

/**
 * 直角边框
 */
public class AngleBorder implements Border{
    @Override
    public char[] getBorderCharacters() {
        return new char[]{ '─', '│', '─', '│', '┌', '┐', '┘', '└' };
    }
}
