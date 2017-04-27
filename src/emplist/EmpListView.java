/*
 * EmpListView.java
 */

package emplist;

import business.Employee;
import business.EmpIO;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The application's main frame.
 */
public class EmpListView extends FrameView {
    
    Map<Long, Employee> emps; // Map is superclass, we're instantiating with a Hashmap or Treemap (inheritance)
    Map<String, Employee> empsByName;
    JTextField[] fields;
    boolean loading = false;
    Map<String, JTextField> screenmap;

    public EmpListView(SingleFrameApplication app) {
        super(app);

        initComponents();
        
        JTextField[] fld = {jtxtEmpNo, jtxtLastNm, jtxtFirstNm,
            jtxtMiddleNm, jtxtSuffix, jtxtAddr1, jtxtAddr2, jtxtCity,
            jtxtState, jtxtZip, jtxtPhone, jtxtGender, jtxtStatus,
            jtxtHireDt, jtxtTerminateDt, jtxtPayCd };
        fields = fld;
        
        screenmap = new HashMap<>();
        for(JTextField f : fields) {
            // want screenmap to map a get method name
            String getnm = "get" + f.getName().substring(4); // begin in JTextField name after the jtxt portion
            screenmap.put(getnm, f);
        }
        
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = EmpListApp.getApplication().getMainFrame();
            aboutBox = new EmpListAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        EmpListApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jradHashMap = new javax.swing.JRadioButton();
        jradTreeMap = new javax.swing.JRadioButton();
        jradNameKey = new javax.swing.JRadioButton();
        cmbKeys = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jtxtEmpNo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtLastNm = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtFirstNm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxtMiddleNm = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxtSuffix = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtxtAddr1 = new javax.swing.JTextField();
        jtxtAddr2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtxtCity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtxtState = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtxtZip = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jtxtPhone = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtxtGender = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtxtStatus = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtxtHireDt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jtxtTerminateDt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jtxtPayCd = new javax.swing.JTextField();
        jbtnNext = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jmnuLoadCSV = new javax.swing.JMenuItem();
        jmnuSaveCSV = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        buttonGroup1 = new javax.swing.ButtonGroup();

        mainPanel.setName("mainPanel"); // NOI18N

        buttonGroup1.add(jradHashMap);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(emplist.EmpListApp.class).getContext().getResourceMap(EmpListView.class);
        jradHashMap.setText(resourceMap.getString("jradHashMap.text")); // NOI18N
        jradHashMap.setName("jradHashMap"); // NOI18N
        jradHashMap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jradHashMapItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jradTreeMap);
        jradTreeMap.setText(resourceMap.getString("jradTreeMap.text")); // NOI18N
        jradTreeMap.setToolTipText(resourceMap.getString("jradTreeMap.toolTipText")); // NOI18N
        jradTreeMap.setName("jradTreeMap"); // NOI18N
        jradTreeMap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jradTreeMapItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jradNameKey);
        jradNameKey.setText(resourceMap.getString("jradNameKey.text")); // NOI18N
        jradNameKey.setName("jradNameKey"); // NOI18N
        jradNameKey.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jradNameKeyItemStateChanged(evt);
            }
        });

        cmbKeys.setFont(resourceMap.getFont("cmbKeys.font")); // NOI18N
        cmbKeys.setName("cmbKeys"); // NOI18N
        cmbKeys.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKeysItemStateChanged(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setToolTipText(resourceMap.getString("jLabel1.toolTipText")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setName("jPanel1"); // NOI18N

        jtxtEmpNo.setName("jtxtEmpNo"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setToolTipText(resourceMap.getString("jLabel3.toolTipText")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jtxtLastNm.setName("jtxtLastNm"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setToolTipText(resourceMap.getString("jLabel4.toolTipText")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jtxtFirstNm.setName("jtxtFirstNm"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setToolTipText(resourceMap.getString("jLabel5.toolTipText")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jtxtMiddleNm.setName("jtxtMiddleNm"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setToolTipText(resourceMap.getString("jLabel6.toolTipText")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jtxtSuffix.setName("jtxtSuffix"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel7.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setToolTipText(resourceMap.getString("jLabel7.toolTipText")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jtxtAddr1.setName("jtxtAddr1"); // NOI18N

        jtxtAddr2.setName("jtxtAddr2"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel8.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setToolTipText(resourceMap.getString("jLabel8.toolTipText")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jtxtCity.setName("jtxtCity"); // NOI18N

        jLabel9.setFont(resourceMap.getFont("jLabel9.font")); // NOI18N
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setToolTipText(resourceMap.getString("jLabel9.toolTipText")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jtxtState.setName("jtxtState"); // NOI18N

        jLabel10.setFont(resourceMap.getFont("jLabel10.font")); // NOI18N
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setToolTipText(resourceMap.getString("jLabel10.toolTipText")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jtxtZip.setName("jtxtZip"); // NOI18N

        jLabel11.setFont(resourceMap.getFont("jLabel11.font")); // NOI18N
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setToolTipText(resourceMap.getString("jLabel11.toolTipText")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jtxtPhone.setName("jtxtPhone"); // NOI18N

        jLabel12.setFont(resourceMap.getFont("jLabel12.font")); // NOI18N
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setToolTipText(resourceMap.getString("jLabel12.toolTipText")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jtxtGender.setName("jtxtGender"); // NOI18N

        jLabel13.setFont(resourceMap.getFont("jLabel13.font")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setToolTipText(resourceMap.getString("jLabel13.toolTipText")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jtxtStatus.setName("jtxtStatus"); // NOI18N

        jLabel14.setFont(resourceMap.getFont("jLabel14.font")); // NOI18N
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setToolTipText(resourceMap.getString("jLabel14.toolTipText")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jtxtHireDt.setName("jtxtHireDt"); // NOI18N

        jLabel15.setFont(resourceMap.getFont("jLabel15.font")); // NOI18N
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setToolTipText(resourceMap.getString("jLabel15.toolTipText")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jtxtTerminateDt.setName("jtxtTerminateDt"); // NOI18N

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setToolTipText(resourceMap.getString("jLabel16.toolTipText")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        jtxtPayCd.setName("jtxtPayCd"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtxtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtState, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtZip, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel11)
                                        .addGap(12, 12, 12)
                                        .addComponent(jtxtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 4, Short.MAX_VALUE)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtxtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel14)
                                        .addGap(12, 12, 12)
                                        .addComponent(jtxtHireDt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtTerminateDt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(183, 183, 183)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtxtPayCd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jtxtEmpNo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtLastNm, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtFirstNm, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jtxtMiddleNm, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jtxtSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtAddr2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtxtAddr1))))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(3, 3, 3)
                        .addComponent(jtxtSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(3, 3, 3)
                        .addComponent(jtxtMiddleNm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(jtxtFirstNm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtEmpNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtLastNm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtxtAddr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtAddr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jtxtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jtxtZip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jtxtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jtxtPayCd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jtxtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15)
                        .addComponent(jtxtTerminateDt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtxtHireDt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jbtnNext.setText(resourceMap.getString("jbtnNext.text")); // NOI18N
        jbtnNext.setName("jbtnNext"); // NOI18N
        jbtnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jradHashMap)
                .addGap(54, 54, 54)
                .addComponent(jradTreeMap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jradNameKey)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbKeys, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnNext))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jradHashMap)
                    .addComponent(jradTreeMap)
                    .addComponent(jradNameKey))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbKeys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnNext))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jmnuLoadCSV.setText(resourceMap.getString("jmnuLoadCSV.text")); // NOI18N
        jmnuLoadCSV.setName("jmnuLoadCSV"); // NOI18N
        jmnuLoadCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuLoadCSVActionPerformed(evt);
            }
        });
        fileMenu.add(jmnuLoadCSV);

        jmnuSaveCSV.setText(resourceMap.getString("jmnuSaveCSV.text")); // NOI18N
        jmnuSaveCSV.setName("jmnuSaveCSV"); // NOI18N
        jmnuSaveCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnuSaveCSVActionPerformed(evt);
            }
        });
        fileMenu.add(jmnuSaveCSV);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(emplist.EmpListApp.class).getContext().getActionMap(EmpListView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 433, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jmnuLoadCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuLoadCSVActionPerformed
        
        statusMessageLabel.setText("");
        
        // do load then choose button
        buttonGroup1.clearSelection();
        
        clearForm();
        JFileChooser f = new JFileChooser(".");
        f.setDialogTitle("Select Employee File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File (.csv)", "csv");
        
        f.setFileFilter(filter);
        JDialog dg = new JDialog(); // show open window, container for the chooser pop up
        int rval = f.showOpenDialog(dg);
        if (rval == JFileChooser.CANCEL_OPTION) {
            statusMessageLabel.setText("Open canceled.");            
        } else {
            // read and return hashmap of file contents
            this.emps = EmpIO.getEmps(f.getSelectedFile().getAbsolutePath());
            statusMessageLabel.setText("Size of emps = " + emps.size());
        }
    }//GEN-LAST:event_jmnuLoadCSVActionPerformed

    private void jradHashMapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jradHashMapItemStateChanged
        
        if(jradHashMap.isSelected()) {
            // clear form
            clearForm();
            cmbKeys_build(); // build the combo box with the hashmap keys
        }
    }//GEN-LAST:event_jradHashMapItemStateChanged

    private void cmbKeysItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKeysItemStateChanged
        
        if(loading) {
            return;
        }
        statusMessageLabel.setText("");
        if(cmbKeys.getSelectedIndex() == -1) {
            statusMessageLabel.setText("No employee selected");
            return;
        }
        Employee emp = null;
        if(jradNameKey.isSelected()) {
            emp = (Employee)empsByName.get((String) cmbKeys.getSelectedItem());
        } else {
            Long eno = (Long) cmbKeys.getSelectedItem();
            emp = (Employee) emps.get(eno);            
        }
        DisplayValues(emp);
    }//GEN-LAST:event_cmbKeysItemStateChanged

    private void jradTreeMapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jradTreeMapItemStateChanged

        if(jradTreeMap.isSelected()) {
            // clear form
            clearForm();
            cmbKeys_build(); // build the combo box with the hashmap keys
        }
    }//GEN-LAST:event_jradTreeMapItemStateChanged

    private void jradNameKeyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jradNameKeyItemStateChanged
        
        if(jradNameKey.isSelected()) {
            clearForm();
            // build new map with name keys
            empsByName = new TreeMap<>();
            for(Map.Entry<Long, Employee> entry : emps.entrySet()) {
                Employee e = entry.getValue();
                String nm = e.getLastNm() + ", " + e.getFirstNm() + " " + e.getMiddleNm();
                empsByName.put(nm, e);
            }
            cmbKeys_build();
        }
    }//GEN-LAST:event_jradNameKeyItemStateChanged

    private void jbtnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNextActionPerformed
        // to get here, they must have selected something in the combo box so we can use it as the controller
        statusMessageLabel.setText("");
        int i = cmbKeys.getSelectedIndex();
        if(i < cmbKeys.getItemCount() - 1) {
            cmbKeys.setSelectedIndex(i + 1); // changes state changed event so it fires
        } else {
            statusMessageLabel.setText("At end of employee list.");
        }
    }//GEN-LAST:event_jbtnNextActionPerformed

    private void jmnuSaveCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnuSaveCSVActionPerformed
        statusMessageLabel.setText("");
        
        
        buttonGroup1.clearSelection();
        
        clearForm();
        JFileChooser f = new JFileChooser(".");
        f.setDialogTitle("Select Employee Output File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File (.csv)", "csv");
        
        f.setFileFilter(filter);
        JDialog dg = new JDialog(); // show open window, container for the chooser pop up
        int rval = f.showSaveDialog(dg);
        if (rval == JFileChooser.CANCEL_OPTION) {
            statusMessageLabel.setText("Save canceled.");            
        } else {
            // read and return hashmap of file contents
            String msg = EmpIO.setEmps(f.getSelectedFile().getAbsolutePath(), emps);
            statusMessageLabel.setText(msg);
        }
    }//GEN-LAST:event_jmnuSaveCSVActionPerformed

    
    
    private void cmbKeys_build() {
        
        loading = true;
        cmbKeys.removeAllItems();
        
        if (jradHashMap.isSelected()) {
            ArrayList<Long> keys = new ArrayList<>(emps.keySet());
            for (Long k : keys) {
                cmbKeys.addItem(k);
            }
            cmbKeys.setSelectedIndex(-1); // default state is no selection
        } else if(jradTreeMap.isSelected()) {
            // an alternative to building the key set, pull keys entry by entry
            TreeMap<Long, Employee> treemap = new TreeMap<>(emps);
            for(Map.Entry<Long, Employee> entry : treemap.entrySet()) {
                Long k = entry.getKey();
                cmbKeys.addItem(k);
            }
            cmbKeys.setSelectedIndex(-1);
        } else if (jradNameKey.isSelected()) {
            for(Map.Entry<String, Employee> entry : empsByName.entrySet()) {
                cmbKeys.addItem(entry.getKey());
            }
            cmbKeys.setSelectedIndex(-1);
        } else {
            statusMessageLabel.setText("No map selected for build.");
        }
        loading = false;
    }
    
    
    private void DisplayValues(Employee emp) {
        
        clearForm();
        // use reflection must obtain class definition for the object
        Class empclass = emp.getClass();
        Method[] methods = empclass.getMethods();
        
        try {
            for(Method m : methods) {
                if(screenmap.containsKey(m.getName())) {
                    JTextField f = screenmap.get(m.getName());
                    switch (m.getName() ) {
                        case "getEmpNo":
                        case "getPhone":
                            Long eno = (Long) m.invoke(emp);
                            f.setText(String.valueOf(eno));
                            break;
                        case "getPayCd":
                            int phn = (int) m.invoke(emp);
                            f.setText(String.valueOf(phn));
                            break;
                        default:
                            String v = (String) m.invoke(emp);
                            f.setText(v);
                            break;
                    } // end switch
                } // end if
            } // end for
        } catch (Exception e) {
            statusMessageLabel.setText("Get Method error:" + e.getMessage());
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbKeys;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnNext;
    private javax.swing.JMenuItem jmnuLoadCSV;
    private javax.swing.JMenuItem jmnuSaveCSV;
    private javax.swing.JRadioButton jradHashMap;
    private javax.swing.JRadioButton jradNameKey;
    private javax.swing.JRadioButton jradTreeMap;
    private javax.swing.JTextField jtxtAddr1;
    private javax.swing.JTextField jtxtAddr2;
    private javax.swing.JTextField jtxtCity;
    private javax.swing.JTextField jtxtEmpNo;
    private javax.swing.JTextField jtxtFirstNm;
    private javax.swing.JTextField jtxtGender;
    private javax.swing.JTextField jtxtHireDt;
    private javax.swing.JTextField jtxtLastNm;
    private javax.swing.JTextField jtxtMiddleNm;
    private javax.swing.JTextField jtxtPayCd;
    private javax.swing.JTextField jtxtPhone;
    private javax.swing.JTextField jtxtState;
    private javax.swing.JTextField jtxtStatus;
    private javax.swing.JTextField jtxtSuffix;
    private javax.swing.JTextField jtxtTerminateDt;
    private javax.swing.JTextField jtxtZip;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    
    public void clearForm() {
        
        for(JTextField f : fields) {
            f.setText("");
        }
    }
}
