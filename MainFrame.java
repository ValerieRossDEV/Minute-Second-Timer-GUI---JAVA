import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame {

	private JFrame frmBing;
	private JTextField minutesTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmBing.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBing = new JFrame();
		frmBing.setTitle("BING");
		frmBing.setBounds(100, 100, 450, 300);
		frmBing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel timeToMsgLabel = new JLabel("Zeit bis zur Meldung:");
		timeToMsgLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JSlider minutesSlider = new JSlider();
		minutesSlider.setMaximum(1440);
		minutesSlider.setMinimum(1);
		minutesSlider.setValue(1);
		minutesSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			minutesTextField.setText(" " + minutesSlider.getValue() );
			}
		});
		
		minutesTextField = new JTextField(" ");
		minutesTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				minutesSlider.setValue( Integer.parseInt (minutesTextField.getText() ) );
				}
			catch (NumberFormatException e1) { }
			}
		});
		minutesTextField.setColumns(10);
		minutesTextField.setText("1");
		
		JLabel minutesLabel = new JLabel("Minuten");
		minutesLabel.setLabelFor(minutesSlider);
		minutesLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel remainingLabel = new JLabel("Verbleibende Zeit:");
		remainingLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel remainingMinLabel = new JLabel("");
		remainingMinLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton startButton = new JButton("Start");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			startButton.setEnabled(false);
			
			final long start = System.currentTimeMillis();
			final long end = start + minutesSlider.getValue()*60*1000;
			
			final javax.swing.Timer timer = new javax.swing.Timer ( 100, null);
			timer.addActionListener(new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					long now = System.currentTimeMillis();
					if (now >=end )
					{
						remainingMinLabel.setText( "" );
						startButton.setEnabled( true );
						JOptionPane.showMessageDialog(null, "BING BING BING!");
						timer.stop();
					}
					else 
						remainingMinLabel.setText((end - now) / 1000 + " Sekunden");
				}
			});
			timer.start();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmBing.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(timeToMsgLabel)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(startButton)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(minutesSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(minutesTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(minutesLabel, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(31, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(remainingLabel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(remainingMinLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(226, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(minutesTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(minutesLabel)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addComponent(timeToMsgLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(minutesSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(remainingLabel)
						.addComponent(remainingMinLabel))
					.addGap(35)
					.addComponent(startButton)
					.addGap(47))
		);
		frmBing.getContentPane().setLayout(groupLayout);
	}
}
