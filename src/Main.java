import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException{
        try {
            FileW.createDir();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        JFrame f=new JFrame("The Gold IDE");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().setBackground(Color.BLACK);

        final JTextArea mCode=new JTextArea();
        mCode.setText(FileW.readFromFile(VGlobals.curFile));
        mCode.setBackground(Color.black);
        mCode.setForeground(Color.lightGray);
        mCode.setLineWrap(true);
        mCode.setWrapStyleWord(true);
        mCode.setFont(mCode.getFont().deriveFont(16f));
        mCode.setEditable(true);
        mCode.setPreferredSize(new Dimension((int) (width-270), (int) (height-160)));
        mCode.setMaximumSize(new Dimension((int) (width-270), (int) (height-160)));
        JScrollPane mcScroll = new JScrollPane(mCode);
        mcScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mcScroll.setBounds(225,30, (int) (width-270), (int) (height-160));

//        final JTextArea mLines=new JTextArea();
//        mLines.setBackground(Color.black);
//        mLines.setForeground(Color.lightGray);
//        mLines.setLineWrap(true);
//        mLines.setWrapStyleWord(true);
//        mLines.setFont(mCode.getFont().deriveFont(16f));
//        mLines.setEditable(false);
//        JScrollPane mlScroll = new JScrollPane(mCode);
////        mlScroll.setBounds(200,30, 20, (int) (height-160));
//        mlScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        for(int i=0; i < countLines(mCode.getText()); i++){
//            mCode.insert(i + "\n", mCode.getText().length());
//        }

        final JTextArea consol=new JTextArea();
        consol.setBounds(225, (int) height -130, (int) (width-225), 30);
        consol.setBackground(Color.darkGray);
        consol.setForeground(Color.gray);
        consol.setText("Output. File on execute: " + VGlobals.curFile);
        consol.setEditable(false);

        final JTextArea mOut=new JTextArea();
        mOut.setBounds(225, (int) height -100, (int) (width-225), 150);
        mOut.setBackground(Color.darkGray);
        mOut.setForeground(Color.white);
        mOut.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    Thread t = new Thread(() ->
                    {
                        try {
                            Run.wrt(extractLine(mOut.getText(), mOut.getText().length()-1));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    t.setName("getplz");
                    t.setDaemon(true);
                    t.run();

                }
            }
        });
        mOut.setEditable(true);

        final JTextArea fileEx=new JTextArea();
        fileEx.setBounds(0, 0, 200, (int) height);
        fileEx.setBackground(Color.black);
        fileEx.setForeground(Color.lightGray);
        fileEx.setEditable(false);
        fileEx.setText(String.valueOf(FileW.listFiles(String.valueOf(Paths.get(VGlobals.home, "AU Projects")))));

        JButton bRun=new JButton("Run");
        bRun.setBackground(Color.gray);
        bRun.setForeground(Color.darkGray);
        bRun.setBounds((int) (width-50)/2,0,100,30);
        bRun.addActionListener(e -> {
            try {
                Files.write(VGlobals.curFile, mCode.getText().getBytes());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                mOut.setText(Run.run());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        f.add(bRun);f.add(mOut);f.add(fileEx);f.add(consol);f.add(mcScroll);
        f.setSize((int) width, (int) height);
        f.setLayout(null);
        f.setVisible(true);
        f.pack();
    }
    private static String extractLine(String input, int line) {
        String[] arrOfstr = input.split("\n");
        if (arrOfstr.length >= line)
            return arrOfstr[line - 1];
        return "";
    }

    private static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return lines.length;
    }
}
