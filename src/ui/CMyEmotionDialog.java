package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jnativehook.NativeHookDemo;

import core.CExperimentTimer;
import core.CWritter;
import enums.EEmotionEnum;
import utils.CAppConstants;

public class CMyEmotionDialog extends JDialog {

	private static final long serialVersionUID = -5708514793042710311L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public CMyEmotionDialog(CExperimentTimer countdownTimer) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel textPane = new JPanel();
			textPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(textPane, BorderLayout.NORTH);
			{
				JLabel label = new JLabel(CAppConstants.EMOTION_QUESTION_STR);
				textPane.add(label);
			}
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton positiveButton = new JButton(CAppConstants.POSITIVE_STR);				
				positiveButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						CWritter.getInstance();
						//CDataCollecting.getInstance().writeText("emotion=POSITIVE");
						CWritter.writeText("DIALOG="+EEmotionEnum.POSITIVE.toString());
						NativeHookDemo.getInstance().getPositiveRadioButton().setSelected(true);
						setVisible(false);
						
						CWritter.getInstance().setEmotion(EEmotionEnum.POSITIVE.toString());
						
						countdownTimer.resetTimer(CAppConstants.DIALOG_TIMER);
					}
				});
				
				buttonPane.add(positiveButton);				
			}
			{
				JButton neutralButton = new JButton(CAppConstants.NEUTRAL_STR);				
				neutralButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						CWritter.getInstance();
						//CDataCollecting.getInstance().writeText("emotion=NEUTRAL");
						CWritter.writeText("DIALOG="+EEmotionEnum.NEUTRAL.toString());
						NativeHookDemo.getInstance().getNeutralRadioButton().setSelected(true);
						setVisible(false);
						
						CWritter.getInstance().setEmotion(EEmotionEnum.NEUTRAL.toString());
						
						countdownTimer.resetTimer(CAppConstants.DIALOG_TIMER);
						
						
					}
				});
				buttonPane.add(neutralButton);
			}
			{
				JButton negativeButton = new JButton(CAppConstants.NEGATIVE_STR);
				negativeButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						CWritter.getInstance();
						//CDataCollecting.getInstance().writeText("NEGATIVE");
						CWritter.writeText("DIALOG="+EEmotionEnum.NEGATIVE.toString());
						NativeHookDemo.getInstance().getNegativeRadioButton().setSelected(true);
						setVisible(false);
						
						CWritter.getInstance().setEmotion(EEmotionEnum.NEGATIVE.toString());
						
						countdownTimer.resetTimer(CAppConstants.DIALOG_TIMER);
					}
				});
				buttonPane.add(negativeButton);				
			}
		}
		
		this.setResizable(false);
		this.setSize(300, 100);
		this.setModal(true);
		this.setAlwaysOnTop(true);
		this.setTitle(CAppConstants.EMOTION_STR);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

}
