package ihm;

import ihmAdherent.AdherentGUI;

public abstract class IhmControl {
	
	protected AdherentGUI adherentApp;

	public void setMainApp(AdherentGUI adherentApp) {
		this.adherentApp = adherentApp;
	}

}
