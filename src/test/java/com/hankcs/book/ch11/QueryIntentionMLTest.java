package com.hankcs.book.ch11;

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.corpus.FileDataSet;
import com.hankcs.hanlp.classification.corpus.IDataSet;
import com.hankcs.hanlp.classification.corpus.MemoryDataSet;
import com.hankcs.hanlp.classification.statistics.evaluations.Evaluator;
import com.hankcs.hanlp.classification.statistics.evaluations.FMeasure;
import com.hankcs.hanlp.classification.tokenizers.BigramTokenizer;
import com.hankcs.hanlp.classification.tokenizers.BigramTokenizerV2;
import com.hankcs.hanlp.classification.tokenizers.HanLPTokenizer;
import com.hankcs.hanlp.classification.tokenizers.ITokenizer;

import java.io.IOException;

import static com.hankcs.demo.DemoTextClassification.CORPUS_FOLDER;

public class QueryIntentionMLTest {
    public static void main(String[] args) throws IOException
    {
//        evaluate(new NaiveBayesClassifier(), new HanLPTokenizer());
        evaluate(new NaiveBayesClassifier(), new BigramTokenizer());
        evaluate(new NaiveBayesClassifier(), new BigramTokenizerV2());
        // 需要引入 https://github.com/hankcs/text-classification-svm ，或者将下列代码复制到该项目运行
        // evaluate(new NaiveBayesClassifier(), new HanLPTokenizer());
        // evaluate(new NaiveBayesClassifier(), new BigramTokenizer());

    }

    public static void evaluate(IClassifier classifier, ITokenizer tokenizer) throws IOException
    {
        String fileName = "/tmp/dw_sm_intention_data_tmp.csv";
        IDataSet trainingCorpus = new FileDataSet().                          // FileDataSet省内存，可加载大规模数据集
                setTokenizer(tokenizer).                               // 支持不同的ITokenizer，详见源码中的文档
                load(fileName, "UTF-8", 0.9);               // 前90%作为训练集
        classifier.train(trainingCorpus);
        IDataSet testingCorpus = new MemoryDataSet(classifier.getModel()).
                load(fileName, "UTF-8", -0.1);        // 后10%作为测试集
        // 计算准确率
        FMeasure result = Evaluator.evaluate(classifier, testingCorpus);
        System.out.println(classifier.getClass().getSimpleName() + "+" + tokenizer.getClass().getSimpleName());
        System.out.println(result);
    }
}
