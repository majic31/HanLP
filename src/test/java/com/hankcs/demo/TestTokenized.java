package com.hankcs.demo;
import com.hankcs.hanlp.classification.tokenizers.BigramTokenizer;
import com.hankcs.hanlp.classification.tokenizers.BigramTokenizerV2;
import com.hankcs.hanlp.classification.tokenizers.GameLPTokenizer;
import com.hankcs.hanlp.classification.tokenizers.HanLPTokenizer;

public class TestTokenized {
    public static void main(String[] args) {
        System.out.println("----------- gamelp ---------");
        GameLPTokenizer gameLPTokenizer = new GameLPTokenizer();
        String[] segs = gameLPTokenizer.segment("交易猫交易账号安全吗");
        for(String seg : segs){
            System.out.println(seg);
        }
        System.out.println("----------------------------");
        segs = gameLPTokenizer.segment("草原神犬");
        for(String seg : segs){
            System.out.println(seg);
        }
        System.out.println("----------------------------");
        BigramTokenizer tokenize = new BigramTokenizer();
        segs = tokenize.segment("访问 https://119fj.xyz/?tg=9713708");
        for(String seg : segs){
            System.out.println(seg);
        }

        System.out.println("----------- bigram ---------");
        BigramTokenizerV2 tokenize2 = new BigramTokenizerV2();
        segs = tokenize2.segment("原神");
        for(String seg : segs){
            System.out.println(seg);
        }
        System.out.println("----------- biggram ---------");
        segs = tokenize2.segment("访问https://119fj.xyz/?tg=9713708");
        for(String seg : segs){
            System.out.println(seg);
        }
//        HanLPTokenizer hanlpTokens = new HanLPTokenizer();
//        segs = hanlpTokens.segment("如果真想用食物解压,建议可以食用燕麦");
//        System.out.println("------  hanlp tokenize ------");
//        for(String seg : segs){Co
//            System.out.println(seg);
//        }
    }
}
