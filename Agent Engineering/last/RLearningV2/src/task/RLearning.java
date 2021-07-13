package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import Agent.Agent;
import Agent.SoftMaxRacer;
import Agent.eGreedyRacer;

import environment.Environment;


/**
 * 
 * Reinforcement Learning General-Purpose Tool 強化学習汎用ツール
 * 
 * 	特徴：MDPを csv 形式で与えることで、状況別の強化学習を行うことができる。
 * 	行為選択；εグリーディー戦略、またはソフトマックス戦略
 * 	実行結果：csv形式のログファイル、方策ファイル、Q値ファイル
 *
 * @author shiro@info.kindai.ac.jp
 * @version 2.0, 07/18/2011
 */

public class RLearning {

	/**
	 * MDP csv ファイル
	 */	
	public static Scanner MDPFILE = null;

	/**
	 * Log csv ファイル
	 */	
	public static PrintWriter LOGFILE = null;

	/**
	 * Qlearning Policy csv ファイル
	 */	
	public static PrintWriter POLICYFILE = null;

	/**
	 * Qlearning QValue csv ファイル
	 */	
	public static PrintWriter QVALUEFILE = null;

	/**
	 * 環境(Environment)インスタンスへの参照変数
	 */
	public static Environment ENVIRONMENT;

	/**
	 * エージェント(Agent)インスタンスへの参照変数
	 */
	public static Agent	AGENT;

	public RLearning(){
	}

	/**
	 * Gridの準備
	 */	
	/**
	 * @param args
	 */
	private void setup(String[] args) {
		if (args.length!=8){
			System.out.println("パラメータ指定エラー：行為選択 学習率 割引率 学習回数 MDPファイル名 logファイル名 policyファイル名 QValueファイル名");
			System.exit(9);
		}

		/**
		 * 行為選択、学習率、割引率、学習回数のセット
		 */
		String STRATEGY = args[0];
		Agent.α = Double.parseDouble(args[1]);
		Agent.γ = Double.parseDouble(args[2]);
		Agent.MAX_CYCLE = Integer.parseInt(args[3]);
	

		/**
		 * MDPファイル名、logファイル名、policyファイル名、QValueファイル名のセット
		 */
		String MDPFileName = args[4];
		String logFileName = args[5];
		String policyFileName = args[6];
		String QValueFileName = args[7];


		try{
			MDPFILE = new Scanner(new File(MDPFileName));
			LOGFILE = new PrintWriter(new File(logFileName));
			POLICYFILE = new PrintWriter(new File(policyFileName));
			QVALUEFILE = new PrintWriter(new File(QValueFileName));
		} catch (FileNotFoundException e){
			System.out.println(e);
		}

		/**
		 * MDPファイルを用いて環境の準備
		 */
		ENVIRONMENT = new Environment();

		if(STRATEGY.equals("egreedy")) {
			AGENT = new eGreedyRacer(ENVIRONMENT);
		} else if (STRATEGY.equals("softmax")){
			AGENT = new SoftMaxRacer(ENVIRONMENT);    	
		} else {
			System.out.println("*** RLearning Start 行為選択は softmax か 	egreedy のみです。");
			System.exit(1);
		}

		System.out.printf("*** RLearning Start ***\n　 行為選択：%s 学習率：%s 割引率：%s 学習回数：%s\n　　MDPファイル名：%s logファイル名：%s policyファイル名：%s QValueファイル名：%s\n",
				args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);

	}

	/**
	 * MDPの表示
	 */
	private void displayMDP() {
		System.out.println("----------- MDP Print -----------");
		ENVIRONMENT.displayMDP();
	}

	/**
	 * 方策の表示
	 */	
	private void displayPolicy() {
		System.out.println("\n----------- Policy Print -----------");
		AGENT.displayPolicy();
	}

	/**
	 * 方策のcsvファイル出力
	 */	
	private void putPolicy() {
		AGENT.putPolicy();
	}

	/**
	 * Q値の表示
	 */	
	private void displayQValue() {
		System.out.println("\n----------- QValue Print -----------");
		AGENT.displayQValue();
	}

	/**
	 * Q値のcsvファイルの出力
	 */	
	private void putQValue() {
		AGENT.putQValue();
	}

	private void wrapup(){
		MDPFILE.close();
		LOGFILE.close();
		POLICYFILE.close();
		QVALUEFILE.close();
	}

	public static void main(String[] args) {

		RLearning RL = new RLearning();
		
		// MDPの記述されたcsvファイルを用いて、環境を準備
		RL.setup(args);
		
		// セットされたMDPを出力
		RL.displayMDP();
		
		// 強化学習を行う
		AGENT.Qlearning();

		// 学習後の方策とQ値を出力
		RL.displayPolicy();
		RL.displayQValue();

		// 学習後の方策とQ値をcsvファイル形式で出力
		RL.putPolicy();
		RL.putQValue();

		RL.wrapup();

	}

}
