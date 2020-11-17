/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>me@hankcs.com</email>
 * <create-date>16/2/13 PM8:02</create-date>
 *
 * <copyright file="HanLPTokenizer.java" company="码农场">
 * Copyright (c) 2008-2016, 码农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.hanlp.classification.tokenizers;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.perceptron.PerceptronLexicalAnalyzer;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.CustomGampLPTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import com.hankcs.hanlp.tokenizer.lexical.AbstractLexicalAnalyzer;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

/**
 * @author hankcs
 */
public class GameLPTokenizer implements ITokenizer {
    public static String CWSMODELFILE = "";
    public static String DICTFILE = "";
    public static String TRAINFILE = "";

    public String[] segment(String text) {
        List<Term> termList = new CustomGampLPTokenizer().segment(text);
        ListIterator<Term> listIterator = termList.listIterator();
        while (listIterator.hasNext())
        {
            Term term = listIterator.next();
            if (term.word.indexOf('\u0000') >= 0)
            {
                listIterator.remove();
            }
        }
        String[] termArray = new String[termList.size()];
        int p = -1;
        for (Term term : termList)
        {
            termArray[++p] = term.word;
        }
        return termArray;
    }
}
