package application.logic;

class CipherClass {
	int[] KEY;

	CipherClass (String keyIn){ //создаём тут ключ
		KEY=new int[keyIn.length()];
		for (int i=0; i<keyIn.length();i++){
			Character c = keyIn.charAt(i);
			int hash = c.hashCode();
			long l = Integer.toUnsignedLong(hash);
			KEY[i]=(int)l+i;
		}
	}


	int code (int bit){ //простое кодирование
		if (bit==0) return bit;
		int answer=bit;
		for(int k:KEY)
			answer=bit^k;
		//System.out.println(answer);
		if (answer==0) return bit;
		return answer;
	}
}
