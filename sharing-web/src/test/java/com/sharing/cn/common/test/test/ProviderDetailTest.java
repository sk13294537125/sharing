package com.sharing.cn.common.test.test;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 2、根据所提供的方法，完成题目：批量根据供应商ID（partnerID）获取供应商下所关联的酒店名称（poiName）以及酒店的联系人姓名（contactName）;
 */
public class ProviderDetailTest {


    @Test
    public void test() throws Exception {
        List<Long> ids = new ArrayList<>();
        for (Long i = 0l; i < 10; i++) {
            ids.add(i);
        }


        System.out.println(JSON.toJSONString(findDetail(ids)));
    }

    /**
     * 方法1）AService.getHotelByParnterId(List<Long> partnerId),根据供应商ID，返回关联的poiId集合;
     * 方法2）BService.batchGetHotelByPoiId(List<Long> poiIds),批量根据酒店id集合，返回酒店信息集合，酒店信息包含酒店ID，酒店名称；
     * 方法3）CService.batchGetContactBypoiIds(List<Long> poiIds),批量根据酒店id集合，返回酒店下联系人信息集合，酒店联系人信息包含酒店ID(poiID),联系人名称（contactName）;
     * @throws Exception
     */
    public List<Partner> findDetail(List<Long> ids) {
        // 方法1）AService.getHotelByParnterId(List<Long> partnerId),根据供应商ID，返回关联的poiId集合;
        List<Long> hotelByPartnerId = getHotelByPartnerId(ids);

        // 方法2）BService.batchGetHotelByPoiId(List<Long> poiIds),批量根据酒店id集合，返回酒店信息集合，酒店信息包含酒店ID，酒店名称；
        List<Poi> pois = batchGetHotelByPoiId(hotelByPartnerId);

        Map<Long, Poi> poiMap = pois.stream()
                .collect(Collectors.toMap(Poi::getPartnerId, Function.identity()));

        // 方法3）CService.batchGetContactBypoiIds(List<Long> poiIds),批量根据酒店id集合，返回酒店下联系人信息集合，酒店联系人信息包含酒店ID(poiID),联系人名称（contactName）;
        List<Contact> contacts = batchGetContactBypoiIds(hotelByPartnerId);

        // 同一个酒店联系人若有多个，任意取一个
        Map<Long, Contact> contactMap = contacts.stream()
                .collect(Collectors.toMap(Contact::getPoiId, c -> c,
                        (v1, v2) -> v2));

        List<Partner> partners = new ArrayList<>();

        for (Long id : ids) {
            Partner partner = new Partner();
            partner.setPartnerId(id);
            Poi poi = poiMap.get(id);
            if (null != poi) {
                partner.setPoiName(poi.getPoiName());
                Contact contact = contactMap.get(poi.getPoiId());
                if (null != contact) {
                    partner.setContactName(contact.getContactName());
                }
            }
            partners.add(partner);
        }
        return partners;
    }

    public List<Long> getHotelByPartnerId(List<Long> partnerId) {
        return partnerId;
    }

    public List<Poi> batchGetHotelByPoiId(List<Long> poiIds) {
        ArrayList<Poi> pois = new ArrayList<>();

        for (Long poiId : poiIds) {
            Poi poi = new Poi();
            poi.setPartnerId(poiId);
            poi.setPoiId(poiId);
            poi.setPoiName(poiId + ";");
            pois.add(poi);
        }
        return pois;
    }

    public List<Contact> batchGetContactBypoiIds(List<Long> poiIds) {
        ArrayList<Contact> pois = new ArrayList<>();

        for (Long poiId : poiIds) {
            Contact poi = new Contact();
            poi.setPoiId(poiId);
            poi.setContactName(poiId + ";");
            pois.add(poi);
            Contact poi1 = new Contact();
            poi1.setPoiId(poiId);
            poi1.setContactName(poiId + ";;");
            pois.add(poi1);
        }
        return pois;
    }

    @Data
    class Partner {
        private Long partnerId;
        private String poiName;
        private String contactName;
    }

    @Data
    class Poi {
        private Long partnerId;
        private Long poiId;
        private String poiName;
    }

    @Data
    class Contact {
        private Long poiId;
        private String contactName;
    }
}
