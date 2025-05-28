package eai.devass.sinistreat.appli.manager.rest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.eai.org.accesseur.valueObject.ConsultationVO;
import com.eai.org.accesseur.valueObject.PointVenteVO;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.missionnement.valueobjects.parametrage.PrestataireDetailVO;
import eai.devass.missionnement.valueobjects.parametrage.PrestataireVO;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.modele.parametrage.Prestataire;
import eai.devass.sinistreat.appli.restClient.ApiResponse;
import eai.devass.sinistreat.appli.restClient.Banda;
import eai.devass.sinistreat.appli.restClient.ClotureDossierKappa;
import eai.devass.sinistreat.appli.restClient.DocumentKappa;
import eai.devass.sinistreat.appli.restClient.DossierKappa;
import eai.devass.sinistreat.appli.restClient.HonoraireKappa;
import eai.devass.sinistreat.appli.restClient.PositionnementKappa;
import eai.devass.sinistreat.appli.restClient.ProcedureKappa;
import eai.devass.sinistreat.appli.restClient.ReglementTransactionnelKappa;
import eai.devass.sinistreat.appli.restClient.StatutProcedureDossierKappa;
import eai.devass.sinistreat.appli.restClient.VictimeKappa;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.exception.IMessageInfo;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;

@SuppressWarnings("all")

public class RestManager extends EntiteManagerAbst implements IConstantes,
IMessageException   {

    private static final Logger logger = Logger.getLogger(RestManager.class);
    private static final String OUVRIR_DOSSIER_URL_PARAM = "WS_OUVRIR_DOSSIER_KAPPA_URL";
    private static final String CLOTURER_DOSSIER_URL_PARAM = "WS_CLOTURER_DOSSIER_KAPPA_URL";
    private static final String SERVICE_VICTIME_URL_PARAM = "WS_SERVICE_VICTIME_KAPPA_URL";
    private static final String RECUPERER_STATUT_PROCEDURE_URL_PARAM = "WS_RECUPERER_STATUT_PROCEDURE_KAPPA_URL";
    private static final String MAJ_STATUT_PROCEDURE_URL_PARAM = "WS_MAJ_STATUT_PROCEDURE_KAPPA_URL";
    private static final String REGLEMENT_TRANSACTIONNEL_URL_PARAM = "WS_REGLEMENT_TRANSACTIONNEL_KAPPA_URL";
    private static final String POSITIONNEMENT_URL_PARAM = "WS_POSITIONNEMENT_KAPPA_URL";
    private static final String HONORAIRE_URL_PARAM = "WS_HONORAIRE_KAPPA_URL";
    private static final String ENVOI_DOCUMENT_VERS_KAPPA_URL_PARAM = "WS_ENVOI_DOCUMENT_KAPPA_URL";
    private static final String BANDA_KAPPA= "WS_BANDA_KAPPA";
    private static final String USER_TOKEN= "USER_TOKEN";
    private static final String PASS_TOKEN= "PASS_TOKEN";
    private static final String WS_TOKEN= "WS_TOKEN";
    private static final String PROXY_DECISION= "PROXY_DECISION";
    private static final String PROXY_HOST= "PROXY_HOST_RMA2";
    private static final String PROXY_HOST3= "PROXY_HOST_RMA3";
    private static final String PROXY_PORT= "PROXY_PORT_RMA";



    
    public boolean envoyerDocument() {
		return false;
    	
    }

    
    

   
    
    
    public String recupererStatutProcedure(StatutProcedureDossierKappa statutProcedureDossierKappa) {

        final String jsonRequest;
        final String bandaRequest;
        try {
            jsonRequest = mapObjectToJson(statutProcedureDossierKappa);
            Banda banda = new Banda();
            
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        

        final ApiResponse apiResponse = postrest("http://localhost:8090/SINISTREATALPHA/searchSinistre/search", jsonRequest);

        String statutProcedure = apiResponse.getMessage();
        if (apiResponse != null && apiResponse.getMessage() != null
                && !StringUtils.isEmpty(apiResponse.getMessage())){
            if (!apiResponse.getMessage().substring(14,17).equals("-1,") && apiResponse.getStatusCode() == 200) {
                // todo map json to object
            	
            	try {
            		
            		ObjectMapper mapper = new ObjectMapper();

            		List<SinistreVO> studentList = new ArrayList<>();
            		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            		studentList = Arrays.asList(mapper.readValue(statutProcedure, SinistreVO[].class));
            		List<Map<String, Object>> sin =  mapStringListToJson(statutProcedure);
            		sin.isEmpty();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	
            }
            else  if (!apiResponse.getMessage().substring(14,17).equals("-1,") && Integer.parseInt(apiResponse.getMessage().substring(14,17)) == 400) {
                statutProcedure = "Innexistant";
            } else if (apiResponse.getMessage().substring(14,17).equals("-1,")) {
                statutProcedure = "Erreur WS";
            }
        }

        logger.info("statutProcedure: " + apiResponse);

        return statutProcedure;
    }
    
    
    
   

    
    

    private ApiResponse get(String url) {
        logger.info("get(url = [" + url + "])...");

        ApiResponse apiResponse;

        HttpGet request = new HttpGet(url);
        request.setConfig(getRequestConfig());

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            logger.info(response.getStatusLine().getStatusCode());
            logger.info(response.getStatusLine().toString());

            final int statusCode = response.getStatusLine().getStatusCode();
            final String message = response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : null;
            final List<String> errors = new ArrayList<>();

            apiResponse = new ApiResponse(statusCode, message, errors);

        } catch (Exception e) {
            logger.error("Une erreur s'est produite", e);
            apiResponse = new ApiResponse(-1,
                    "ERROR WS", Collections.singletonList(e.getMessage()));
        }

        logger.info("apiResponse: " + apiResponse);

        return apiResponse;
    }

    private ApiResponse post(String url, List<NameValuePair> params) {
        logger.info("post(url = [" + url + "], params = [" + params + "]...");

        ApiResponse apiResponse;

        HttpPost post = new HttpPost(url);
        post.setConfig(getRequestConfig());

        try {
            post.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            logger.error("Une erreur s'est produite", e);
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            logger.info(response.getStatusLine().getStatusCode());
            logger.info(response.getStatusLine().toString());

            final int statusCode = response.getStatusLine().getStatusCode();
            final String message = EntityUtils.toString(response.getEntity());
            final List<String> errors = new ArrayList<>();

            apiResponse = new ApiResponse(statusCode, message, errors);

        } catch (Exception e) {
            logger.error("Une erreur s'est produite", e);
            apiResponse = new ApiResponse(-1,
                    "ERROR WS", Collections.singletonList(e.getMessage()));
        }

        logger.info("apiResponse: " + apiResponse);

        return apiResponse;
    }
    
    
    
    private ApiResponse postrest(String url, String jsonRequest) {
        logger.info("post(url = [" + url + "], jsonRequest = [" + jsonRequest + "]...");

        ApiResponse apiResponse;
        HttpPost post = new HttpPost(url);
        post.setConfig(getRequestConfig());
      /*  
        SinistreVO sin = new SinistreVO();
        sin.setNumeroSinistre("002210002022024001004");
//        jsonRequest= "{ \n" 
//        		+"    \"numeroSinistre\": \""+"002210002022024001004"+"\""
//        		+ "}";

        try {
			jsonRequest = mapObjectToJson(sin);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		*/
        try {
            StringEntity entity = new StringEntity(jsonRequest, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
        } catch (Exception e) {
            logger.error("Une erreur s'est produite", e);
        }
        post.setHeader("Content-type", "application/json");

        TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
                NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> socketFactoryRegistry =
                RegistryBuilder.<ConnectionSocketFactory> create()
                        .register("https", sslsf)
                        .register("http", new PlainConnectionSocketFactory())
                        .build();
        BasicHttpClientConnectionManager connectionManager =
                new BasicHttpClientConnectionManager(socketFactoryRegistry);

        try (
                //CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLSocketFactory(sslsf)
                     .setConnectionManager(connectionManager).setRedirectStrategy(new DefaultRedirectStrategy()).build();
                CloseableHttpResponse response = httpClient.execute(post)) {

            logger.info(response.getStatusLine().getStatusCode());
            logger.info(response.getStatusLine().toString());

            final int statusCode = response.getStatusLine().getStatusCode();
            final String message = EntityUtils.toString(response.getEntity());
            final List<String> errors = new ArrayList<>();

            apiResponse = new ApiResponse(statusCode, message, errors);

        } catch (Exception e) {
            logger.error("Une erreur s'est produite", e);
            apiResponse = new ApiResponse(-1,
                    "ERROR WS", Collections.singletonList(e.getMessage()));
        }

        logger.info("apiResponse: " + apiResponse);

        return apiResponse;
    }

   
   

    private RequestConfig getRequestConfig() {
        int timeout = 30000;
            return RequestConfig.custom()
                    .setConnectionRequestTimeout(timeout)
                    .setConnectTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .build();
    }
/*
    public String paramManager.getValeurParametrage(final String nomParametrage) {
       /*
    	String valeur = null;

        try {
            final List list = dao.executeSqlQuery("SELECT valeur FROM ParametreKappa WHERE nom='" + nomParametrage + "'");
            if (!CollectionUtils.isEmpty(list)) {
                valeur = (String) list.get(0);
            }
        } catch (Exception e) {
            logger.error("Une erreur s'est produite", e);
        }

        return valeur;
       
    	try {
			if (Fonctions.isEmpty(nomParametrage)) {
				return "";
			} else {
				String query = " from ParametreKappa pkappa where pkappa.nom= TO_CHAR(" + nomParametrage + ")";

				ParametreKappa pk = (ParametreKappa) getSession().createQuery(query).uniqueResult();

				if (pk != null) {
					return pk.getValeur();
				} else {
					return "";
				}
			}

		} catch (PersistenceException e) {
			logger.error("Error :", e);
			return "";
		}

        
        
    }
*/
    public static String capitalize(String str)
    {
        if (str == null || str.length() == 0) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    private String mapObjectToJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
       // objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);


        return objectMapper.writeValueAsString(o);
    }
    private   Map<String, Object> mapStringToJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonObject = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
        return jsonObject;
    }
    
    private   List<Map<String, Object>> mapStringListToJson(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> jsonObject = objectMapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});
        return jsonObject;
    }
	

	
	public String getValeurParametrage(String nomParametrage) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
	public PointVenteVO ConsulterOrgService(ConsultationVO consultationVO) {
		// TODO Auto-generated method stub
				final String jsonRequest;
				PointVenteVO pvo = new PointVenteVO();

		        try {
		            jsonRequest = mapObjectToJson(consultationVO);
		            
		        } catch (JsonProcessingException e) {
		            throw new RuntimeException(e);
		        }

		        final ApiResponse apiResponse = postrest("https://proxy-was-apps.apps.ocprmarec.eurafric.com/api/orga/consulter", jsonRequest);
		        
		        
		        //final ApiResponse apiResponse = postrest(paramManager.getValeurParametrage("WS_CHERCHER_PREST"), jsonRequest);
		        

		        String statutProcedure = apiResponse.getMessage();
		        
		        Map<String, Object> map = new HashMap();
		        
		        
		        if (apiResponse != null && apiResponse.getMessage() != null
		                && !StringUtils.isEmpty(apiResponse.getMessage())){
		            if (!apiResponse.getMessage().substring(14,17).equals("-1,") && apiResponse.getStatusCode() == 200) {
		                // todo map json to object
		            	
		            	try {
		            		
		            		ObjectMapper mapper = new ObjectMapper();

		            		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		            		map = mapper.readValue(statutProcedure, HashMap.class);
		            		//map = mapStringToJson(statutProcedure);
		            		//Map<String, Object> pvores = mapStringToJson(map.get("valueObject").toString());
		            		pvo = mapper.convertValue(map.get("valueObject"), PointVenteVO.class);
		            	
		            		//List<Map<String, Object>> sin =  mapStringListToJson(statutProcedure);
		            		//sin.isEmpty();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            	
		            	
		            }
		            else  if (!apiResponse.getMessage().substring(14,17).equals("-1,") && Integer.parseInt(apiResponse.getMessage().substring(14,17)) == 400) {
		                statutProcedure = "Innexistant";
		            } else if (apiResponse.getMessage().substring(14,17).equals("-1,")) {
		                statutProcedure = "Erreur WS";
		            }
		        }

		        logger.info("statutProcedure: " + apiResponse);

		        return pvo;
	}
}
