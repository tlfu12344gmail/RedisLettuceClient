/**
 *
 */
package cn.org.tpeach.nosql.view.dialog;

import cn.org.tpeach.nosql.constant.I18nKey;
import cn.org.tpeach.nosql.constant.PublicConstant;
import cn.org.tpeach.nosql.framework.LarkFrame;
import cn.org.tpeach.nosql.tools.IOUtil;
import cn.org.tpeach.nosql.tools.PropertiesUtils;
import cn.org.tpeach.nosql.tools.SwingTools;
import cn.org.tpeach.nosql.view.component.LinkLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;

/**
 　 * <p>Title: AboutDialog.java</p>
 　 * @author taoyz
 　 * @date 2019年9月8日
 　 * @version 1.0
 */
public class AboutDialog extends BaseDialog<Object, Object>{
	private Font font = new Font("黑体", Font.PLAIN, 16);
	private Font boldFont = new Font("黑体", Font.BOLD, 18);
	private JPanel leftPanel,rightPanel;

	public AboutDialog( ) {
		super(LarkFrame.frame, null);

	}
	@Override
	public void setMinimumSize() {
		this.setMinimumSize(new Dimension(minWidth+150,minHeight+60));
	}

	@Override
	public void initDialog(Object t) {
		this.setTitle(LarkFrame.getI18nFirstUpText(I18nKey.RedisResource.ABOUT));

	}
	@Override
	protected void setMiddlePanel(JPanel middlePanel){

	}
	@Override
	protected void contextUiImpl(JPanel contextPanel, JPanel btnPanel) {
		super.contextUiImpl(contextPanel, btnPanel);

		contextPanel.setLayout(new GridLayout(1, 1));
		leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		contextPanel.add(leftPanel);
		if(PublicConstant.ProjectEnvironment.RELEASE.equals(LarkFrame.getProjectEnv())){
			addRightPanel(contextPanel);
		}


		leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
		leftPanel.add(Box.createVerticalStrut(10));
		Box box = Box.createHorizontalBox();

		JLabel label = new JLabel("Redis");
		label.setFont(boldFont);
		box.add(label);
		leftPanel.add(box);

		label = new JLabel("Redis Database Manager");
		label.setFont(font);
		box = Box.createHorizontalBox();
		box.add(label);
		leftPanel.add(box);

//		label = new JLabel(new ImageIcon(PublicConstant.Image.logo.getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH)));
		label = new JLabel(PublicConstant.Image.getImageIcon( PublicConstant.Image.logo,220, 220)) ;
		box = Box.createHorizontalBox();
		box.add(label);
		leftPanel.add(box);

		label = new JLabel("Version:"+ LarkFrame.APPLICATION_VALUE.get("version"));
		label.setFont(font);
		box = Box.createHorizontalBox();
		box.add(label);
		leftPanel.add(box);

		label = new JLabel("Release data:"+LarkFrame.APPLICATION_VALUE.get("release_data"));
		label.setFont(font);
		box = Box.createHorizontalBox();
		box.add(label);
		leftPanel.add(box);

	}

	private void addRightPanel(JPanel contextPanel){
		rightPanel = new JPanel();
		rightPanel.setBackground(Color.WHITE);
		contextPanel.add(rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		rightPanel.add(Box.createVerticalStrut(10));
		JLabel donateLabel = new JLabel("赞赏");
		donateLabel.setFont(boldFont);
		Box donateBox = Box.createHorizontalBox();
		donateBox.add(donateLabel);
		rightPanel.add(donateBox);
		rightPanel.add(Box.createVerticalStrut(10));

		Box donateImageBox = Box.createHorizontalBox();

		JLabel donateImageLabel = new JLabel(PublicConstant.Image.getImageIcon(PublicConstant.Image.donate));
		donateImageBox.add(donateImageLabel);
		rightPanel.add(donateImageBox);

		Box payImageBox = Box.createHorizontalBox();
		payImageBox.setVisible(false);
		JLabel wechatLabel = new JLabel(PublicConstant.Image.getImageIcon(PublicConstant.Image.wechatpay,150,150));
		JLabel alipayLabel = new JLabel(PublicConstant.Image.getImageIcon(PublicConstant.Image.alipay,150,150));
		alipayLabel.setText("wechat");
		wechatLabel.setText("alipay");
		wechatLabel.setVerticalTextPosition(SwingConstants.TOP);
		wechatLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		alipayLabel.setVerticalTextPosition(SwingConstants.TOP);
		alipayLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		payImageBox.add(wechatLabel);
		payImageBox.add(alipayLabel);
		rightPanel.add(payImageBox);
		Component verticalStrut = Box.createVerticalStrut(30);
		Component verticalStrut2 = Box.createVerticalStrut(55);
		verticalStrut2.setVisible(false);
		rightPanel.add(verticalStrut);
		rightPanel.add(verticalStrut2);
		donateBox = Box.createHorizontalBox();
		JLabel btnLabel = new JLabel("donate");
		btnLabel.setFont(font);
		btnLabel.setOpaque(false);
		btnLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.RED),new EmptyBorder(3,3,3,3)));
		btnLabel.setForeground(Color.RED);
		SwingTools.addMouseClickedListener(btnLabel,e->{
			if("donate".equals(btnLabel.getText())){
				btnLabel.setText("back");
				payImageBox.setVisible(true);
				donateImageBox.setVisible(false);
				verticalStrut.setVisible(false);
				verticalStrut2.setVisible(true);
//				donateLabel.setText("");
			}else if("back".equals(btnLabel.getText())){
				btnLabel.setText("donate");
				payImageBox.setVisible(false);
				donateImageBox.setVisible(true);
				verticalStrut.setVisible(true);
				verticalStrut2.setVisible(false);
			}
		});
		donateBox.add(btnLabel);
		rightPanel.add(donateBox);
		leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(173, 173, 173)));
	}

	@Override
	public Box addBtnToBtnPanel(JPanel btnPanel) {
		btnPanel.setLayout(new BoxLayout(btnPanel,BoxLayout.X_AXIS));
//		btnPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(173, 173, 173)));
		btnPanel.setBackground(Color.white);
		LinkLabel label = new LinkLabel("Gitee","https://gitee.com/tyanzhe/RedisLettuceClient");
		Font font = new Font("黑体", Font.PLAIN, 14);
		label.setFont(font);
		label.setIcon(PublicConstant.Image.getImageIcon(PublicConstant.Image.gitee));
		btnbox.add(btnHorizontalStrut());
		btnbox.add(label);
		label = new LinkLabel("Github","https://github.com/TYanZhe/RedisLettuceClient");
		label.setFont(font);
		label.setIcon(PublicConstant.Image.getImageIcon(PublicConstant.Image.github));
		btnbox.add(btnHorizontalStrut());
		btnbox.add(label);

		label = new LinkLabel("Changelog");
		label.setMouseClieckConsumer(e->{
			FileOutputStream fos=null;
			PrintStream ps=null;
			try {
				File tempFile= IOUtil.getFile(System.getProperty("java.io.tmpdir")+"Changelog.txt");
				fos = new FileOutputStream(tempFile);
				ps = new PrintStream(fos);
				ps.print("");
				InputStream resourceAsStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("changelog.txt");
				IOUtil.writeConfigFile(resourceAsStream,tempFile);
				Desktop.getDesktop().open(tempFile);
				tempFile.deleteOnExit();
			} catch (IOException ex) {
				ex.printStackTrace();
			}finally {
				IOUtil.close(fos,ps);
			}
		});
		label.setFont(font);
		label.setIcon(PublicConstant.Image.getImageIcon(PublicConstant.Image.changeLog));
		btnbox.add(btnHorizontalStrut());
		btnbox.add(label);
		btnbox.add(Box.createHorizontalGlue());
		cancelBtn.setBackground(new Color( 45,189,168));
		cancelBtn.setForeground(Color.WHITE);
		btnbox.add(cancelBtn);
		btnbox.add(btnHorizontalStrut());
		return btnbox;
	}

	@Override
	public boolean isNeedBtn() {
		return true;
	}
}
