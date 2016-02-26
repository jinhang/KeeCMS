package com.kee.cms.service;

//import org.apache.lucene.analysis.TokenStream;
//import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.LongField;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
//import org.apache.lucene.queryparser.classic.ParseException;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.Sort;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.search.highlight.Highlighter;
//import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
//import org.apache.lucene.search.highlight.QueryScorer;
//import org.apache.lucene.search.highlight.SimpleFragmenter;
//import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.RAMDirectory;
//import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
//import com.chenlb.mmseg4j.analysis.SimpleAnalyzer;
/**
 * Lucene 搜索
 * 
 * @author keehang
 * 
 */
@Service
public class LuceneService {

	// private static String LUCENE_DIRECTORY = System
	// .getProperty(SystemConstant.kee_CMS_ROOT) + "WIN-INF/lucene";
	//
	// Directory dir = new RAMDirectory();
	//
	// SmartChineseAnalyzer analyzer = new
	// SmartChineseAnalyzer(Version.LUCENE_45,
	// true);
	//
	// public void add(long fileId, String name, String content, Date
	// createTime) {
	// try {
	// // 1、创建Directory对象
	// // Directory dir = FSDirectory.open(new File(LUCENE_DIRECTORY));
	// // 2、创建indexWrite
	// IndexWriter indexWriter = new IndexWriter(dir,
	// new IndexWriterConfig(Version.LUCENE_45, analyzer));
	// // 3、创建document对象
	// Document document = new Document();
	// // 4、将要索引的数据添加到document
	// document.add(new LongField("fileId", fileId, Field.Store.YES));
	// document.add(new StringField("name", name, Field.Store.YES));
	// document.add(new TextField("content", content, Field.Store.YES));
	// document.add(new LongField("createTime", createTime.getTime(),
	// Field.Store.YES));
	// indexWriter.addDocument(document);
	// indexWriter.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void query(String q) {
	// try {
	// // 1.创建Directory
	// // Directory dir = FSDirectory.open(new File(LUCENE_DIRECTORY));
	// // 2.创建IndexReader
	// IndexReader reader = DirectoryReader.open(dir);
	// // 3.根据IndexReader创建IndexSearcher
	// IndexSearcher searcher = new IndexSearcher(reader);
	// // 4.创建搜索的Query
	// // 创建query，表示搜索域中包含q的文档
	// Query query = MultiFieldQueryParser.parse(Version.LUCENE_45,
	// new String[] { q,q }, new String[] { "name", "content" },
	// analyzer);
	// // 5.根据search搜索返回TopDocs，要设置返回条数
	// TopDocs docs = searcher.search(query, 20, Sort.INDEXORDER);
	// // 6.根据TopDocs获取ScoreDoc
	// for (ScoreDoc doc : docs.scoreDocs) {
	// // 7.根据searcher和scoredoc获取具体的Document对象
	// Document document = searcher.doc(doc.doc);
	// // 8.根据Document对象获取需要的内容
	// String text = document.get("fileId") + " - "
	// + document.get("name") + " - "
	// + document.get("content") + " - "
	// + document.get("createTime");
	// SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter(
	// "<font color='red'>", "</font>");
	// Highlighter highlighter = new Highlighter(simpleHTMLFormatter,
	// new QueryScorer(query));
	// highlighter.setTextFragmenter(new SimpleFragmenter(text
	// .length()));
	// TokenStream tokenStream = analyzer.tokenStream("content",
	// new StringReader(text));
	// String highLightText = highlighter.getBestFragment(tokenStream,
	// text);
	// // System.out.println("★高亮显示第 "+(i+1) +" 条检索结果如下所示：");
	// System.out.println(highLightText);
	// // System.out.println();
	// }
	// // 9.关闭reader
	// reader.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ParseException e) {
	// e.printStackTrace();
	// } catch (InvalidTokenOffsetsException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// public static void main(String[] args) throws IOException, ParseException
	// {
	// LuceneService lucene = new LuceneService();
	// lucene.add(1, "美丽 的人", "中国人包含了从索引中搜索结果的各种类", new Date());
	// lucene.add(2, "美丽 的 人", "怎么人民创建的索引", new Date());
	// lucene.add(3, "聪明 的 人", "怎么创建共和索引", new Date());
	// lucene.add(4, "聪明 的 人", "介绍", new Date());
	//
	// lucene.query("中国人民万岁");
	// // // 一、创建索引
	// // Directory dir = new RAMDirectory();
	// // Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_45);
	// //
	// // IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_45,
	// // analyzer);
	// // IndexWriter indexWriter = new IndexWriter(dir, config);
	// //
	// // Document doc = new Document();
	// // String title = "标题";
	// // String content = "被索引的内容";
	// // Field f1 = new Field("title", title, TextField.TYPE_STORED);
	// // Field f2 = new Field("content", content, TextField.TYPE_STORED);
	// // doc.add(f1);
	// // doc.add(f2);
	// //
	// // indexWriter.addDocument(doc);
	// // indexWriter.close();
	// //
	// // // 二、搜索
	// // DirectoryReader directoryReader = DirectoryReader.open(dir);
	// // IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
	// //
	// // QueryParser parser = new QueryParser(Version.LUCENE_45, "content",
	// // analyzer);
	// // Query query = parser.parse("内容");
	// //
	// // TopDocs topDocs = indexSearcher.search(query, null, 100);
	// // ScoreDoc[] hits = topDocs.scoreDocs;
	// //
	// // System.out.println("查询结果数：" + hits.length);
	// //
	// // for (int n = 0; n < hits.length; n++) {
	// // Document hitDoc = indexSearcher.doc(hits[n].doc);
	// // System.out.println("搜索的结果title：" + hitDoc.get("title"));
	// // }
	// }

}
