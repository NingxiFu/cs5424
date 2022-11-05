package com.nus.cs5424.txs;

import com.nus.cs5424.data.OrderLine;
import com.nus.cs5424.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RelatedCustomer implements transaction{
    @Autowired
    CustomerStorage customerStorage;

    @Autowired
    ItemStorage itemStorage;

    @Autowired
    WarehouseDistrictStorage warehouseDistrictStorage;

    @Autowired
    OrderStorage orderStorage;

    @Autowired
    StockStorage stockStorage;

    @Autowired
    OrderLineStorage orderLineStorage;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void process(String[] args) {
//2.8 Related-Customer Transaction
//inputs:
        int given_w_id = Integer.parseInt(args[1]);
        int given_d_id = Integer.parseInt(args[2]);
        int given_c_id = Integer.parseInt(args[3]);
//transaction:
        Set<Integer> given_i_id_l = new HashSet<>();
        List<String> c_identifiers = new ArrayList<String>();
        List<Integer> given_o_id_l = orderStorage.getAllOrderIdByCustomer(given_w_id, given_d_id, given_c_id);
        for(int given_o_id: given_o_id_l){
            List<OrderLine> given_ol_l = orderLineStorage.getOrderLinesByOneOrder(given_w_id, given_d_id, given_o_id);
            for (OrderLine given_ol: given_ol_l){
                given_i_id_l.add(given_ol.getOl_i_id());
//                System.out.println("\ti_id() = "+given_ol.getOl_i_id());
            }
        }
        for (int w_id = 1; w_id <= 10; w_id++){
//            System.out.println("\n\n\nw_id = "+w_id);
            if (w_id == given_w_id){
                continue;
            }
            for (int d_id = 1; d_id <= 10; d_id++){
//                System.out.println("d_id = "+d_id);
                List<Integer> contain_items_o_id_l = orderLineStorage.getOrderLinesContainItemSetByDistrict(w_id, d_id, given_i_id_l);
                if (contain_items_o_id_l.size() == 0){
//                    System.out.println("contain_items_o_id_l == null");
                    continue;
                }
                else{
//                    System.out.println("contain_items_o_id_l != null");
                    for(int i: contain_items_o_id_l){
//                        System.out.println("contain_items_o_id_l: "+i);
                    }
                    List<Integer> c_id_l = orderStorage.getAllCIdByOIds(w_id, d_id, contain_items_o_id_l);
                    for (int c_id : c_id_l){
//                        System.out.print("\nc_id = "+c_id);
                        List<Integer> o_id_l = orderStorage.getAllOrderIdByCustomer(w_id, d_id, c_id);
                        for (int o_id : o_id_l){
//                            System.out.print("o_id = "+o_id + "   ");
                            List<OrderLine> ols = orderLineStorage.getOrderLinesContainItemSetByOneOrder(w_id, d_id, o_id, given_i_id_l);
                            if (ols != null){
                                int count = 0;
                                for (OrderLine ol: ols){
//                                    System.out.print("ol_count "+ count + "\t");
                                    if(given_i_id_l.contains(ol.getOl_i_id())){
                                        count++;
//                                        System.out.print("-----------ol_count+1 = "+ count + "------------\t");
                                    }
                                    if (count == 2){
                                        String c_identifier = "W_ID: " + w_id + " \tD_ID: " + d_id + " \tC_ID: " +  c_id;

//                                        System.out.println("c_identifier = "+c_identifier+"\n\n\n\n\n\n\n\n");

                                        c_identifiers.add(c_identifier);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
//outputs:
        if(c_identifiers.size() != 0){
            for (String c_identifier: c_identifiers){
                System.out.println(c_identifier);
            }
        }
        else{
            System.out.println("No related customer found.");
        }

    }
}
