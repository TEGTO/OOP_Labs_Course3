package ui;
import apartments.Apartment;
import electricalDevices.IElecDevice;
import electricalDevices.additionalClasses.WorkingMode;
import electricalDevices.types.kitchenDevices.types.Microwave;
import myLogger.MyLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI
{
    public UI(Apartment apartment)
    {
        JFrame Frame = new JFrame();//creating  of JFrame
        JButton button;
        //Print All
        button = new JButton("Print all");//creating instance of JButton
        button.setBounds(50, 50, 120, 40);//x axis, y axis, width, height
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MyLogger.printInfoMessage("\nPrint All!\n");
                apartment.printAllDevices();
            }
        });
        Frame.add(button);//adding button in JFrame
        //Enable All
        button = new JButton("Enable all");//creating instance of JButton
        button.setBounds(180, 50, 120, 40);//x axis, y axis, width, height
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MyLogger.printInfoMessage("\nEnable All!\n");
                apartment.enableAllDevices();
            }
        });
        Frame.add(button);//adding button in JFrame
        //Enable some by random
        button = new JButton("Enable some");//creating instance of JButton
        button.setBounds(310, 50, 120, 40);//x axis, y axis, width, height
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MyLogger.printInfoMessage("\nEnable Some!\n");
                apartment.enableSomeDevices();
            }
        });
        Frame.add(button);//adding button in JFrame
        //Disable all
        button = new JButton("Disable all");//creating instance of JButton
        button.setBounds(440, 50, 120, 40);//x axis, y axis, width, height
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MyLogger.printInfoMessage("\nDisable All!\n");
                apartment.disableAllDevices();
            }
        });
        Frame.add(button);//adding button in JFrame
        //Count power of all
        button = new JButton("Count power of all");//creating instance of JButton
        button.setBounds(50, 100, 120, 40);//x axis, y axis, width, height
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MyLogger.printInfoMessage("\nPower of All!\n");
                MyLogger.printInfoMessage(String.format("\nPower of all: %1$d", apartment.getPowerOfAll()));
            }
        });
        Frame.add(button);//adding button in JFrame
        //Sort by power
        button = new JButton("Sort by power");//creating instance of JButton
        button.setBounds(180, 100, 120, 40);//x axis, y axis, width, height
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                apartment.sortByPower();
            }
        });
        Frame.add(button);//adding button in JFrame
        Frame.add(button);//adding button in JFrame
        //Find by params
        button = new JButton("Find by params");//creating instance of JButton
        button.setBounds(310, 100, 120, 40);//x axis, y axis, width, height
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MyLogger.printInfoMessage("\nFind by Params!\n");
                electricalDevices.additionalClasses.Timer timer = new electricalDevices.additionalClasses.Timer(10);
                IElecDevice device = apartment.findByObject(new Microwave(200, true, WorkingMode.Medium, timer, "Pizza"));
                if (device != null)
                {
                    MyLogger.printInfoMessage("Device is found!");
                    device.printInfo();
                }
                else
                    MyLogger.printInfoMessage("Device is not found!");
            }
        });
        Frame.add(button);//adding button in JFrame
        //Reinitialize
        button = new JButton("Reinitialize data");//creating instance of JButton
        button.setBounds(440, 100, 120, 40);//x axis, y axis, width, height
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                apartment.initializeDevicesData();
            }
        });
        Frame.add(button);//adding button in JFrame
        //SaveData
        button = new JButton("Save Data");//creating instance of JButton
        button.setBounds(580, 75, 120, 40);//x axis, y axis, width, height
        button.setBackground(Color.RED);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                apartment.saveDevicesData();
            }
        });
        Frame.add(button);//adding button in JFrame
        //Layout
        Frame.setSize(750, 200);//400 width and 500 height
        Frame.setLayout(null);//using no layout managers
        Frame.setVisible(true);//making the frame visible
    }
}
