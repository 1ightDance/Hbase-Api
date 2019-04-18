
package com.tensor.api.org.service.solr;

import com.google.gson.JsonObject;

import com.tensor.api.org.enpity.News;

import com.tensor.api.org.enpity.ResultData;

import reactor.core.publisher.Mono;

public interface SolrNewService {

	/**

     * ���ݴ���Ĳ��������Ų������ݿ� �����Ƿ�ɹ�

     *

     * @param news

     * @return

     */

    Mono<ResultData<Boolean>> putNews(News news);
   /**
     * ��ȡȫ������

     *

     * @return

     */

    Mono<ResultData<JsonObject>> getAllNews();


    /**

     * �����м���ȡ���� ���ض�Ӧ����

     *

     * @param rowKey

     * @return

     */

    Mono<ResultData<JsonObject>> getNewsByRowKey(String rowKey);

    /**

     * ����  ��ѯ

     *

     * @param rowkey

     * @return

     */
    Mono<ResultData<JsonObject>> queryNewsByAuthor(String author);
    /**

     * ����  ��ѯ

     *

     * @param author

     * @return

     */
    Mono<ResultData<JsonObject>> queryNewsByTitle(String newTitle);
    
    /**

     * �����ѯ  ��ѯ

     *

     * @param newType

     * @return

     */
    Mono<ResultData<JsonObject>> queryNewsByType(String newType);
    
}
