package com.netrobol.tools;

import java.io.File;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.Wallet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ViewWallet {
	final static NetworkParameters netParams = TestNet3Params.get();
	private final static String WALLET_DIRECTORY = "./wallets/";

	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				log.error("Specify wallet file as argument");
				return;
			}
			File walletFile = new File(WALLET_DIRECTORY + args[0]);
			if (!walletFile.exists()) {
				log.error("Wallet file {} doesn't exist", walletFile.getAbsolutePath());
			}
			Wallet wallet = Wallet.loadFromFile(walletFile);
			log.debug("Wallet: {}", wallet);

			Address a = wallet.currentReceiveAddress();
			log.debug("Receive address: {}", a);

			wallet.saveToFile(walletFile);

		} catch (Exception e) {
			log.error("Problem occurred", e);
		}
	}
}
