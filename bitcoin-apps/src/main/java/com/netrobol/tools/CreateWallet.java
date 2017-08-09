package com.netrobol.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.wallet.Wallet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateWallet {
	private final static String WALLET_DIRECTORY = "./wallets/";

	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				log.error("Please provide passphrase as argument");
				return;
			}
			NetworkParameters netParams = TestNet3Params.get();
			String networkId = netParams.getId();

			log.debug("Network params: {}", networkId);
			Wallet wallet = new Wallet(netParams);
			wallet.encrypt(args[0]);
			addKeys(wallet, args[0], 10);

			log.debug("Wallet: {}", wallet);

			saveWallet(networkId, wallet);

		} catch (Exception e) {
			log.error("Problem occurred", e);
		}
	}

	private static void saveWallet(String networkId, Wallet wallet) throws IOException {
		File f = new File(WALLET_DIRECTORY);
		f.mkdirs();

		wallet.saveToFile(new File(WALLET_DIRECTORY + networkId + "." + System.currentTimeMillis() + ".wallet"));
	}

	private static void addKeys(Wallet wallet, String passkey, int maxKeys) {
		List<ECKey> keys = new ArrayList<>();
		for (int i = 0; i < maxKeys; i++) {
			keys.add(new ECKey());
		}
		wallet.importKeysAndEncrypt(keys, passkey);
	}
}
