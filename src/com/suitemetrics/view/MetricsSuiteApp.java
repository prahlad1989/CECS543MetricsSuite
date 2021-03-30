/**
 * 
 */
package com.suitemetrics.view;

import javax.swing.SwingUtilities;

/**
 *
 */
public class MetricsSuiteApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}

}
