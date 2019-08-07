package com.lot.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lot.model.Item;
import com.lot.model.Items;
import com.lot.model.Lot_Lager;
import com.lot.model.Lot_Order;
import com.lot.model.Order;
import com.lot.model.Order_data;
import com.lot.model.Payment;
import com.lot.model.Sell_To;
import com.lot.model.Ship_To;
import com.lot.model.Shipment;
import com.lot.repository.BillingAddressRepository;
import com.lot.repository.LotOrderRepository;
import com.lot.repository.LotRepository;
import com.lot.repository.Lot_LagerRepository;
import com.lot.repository.ShippingAddressRepository;
import com.lot.repository.UserRepository;

@Service
public class LotOrderService {

	@Autowired
	LotOrderRepository lotOrderRepository;

	@Autowired
	LotRepository lotRepository;

	@Autowired
	Lot_LagerRepository lot_LagerRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BillingAddressRepository billingAddressRepository;

	@Autowired
	ShippingAddressRepository shippingAddressRepository;

	@Autowired
	LotOrderService lotOrderService;

	public Order_data processOrderdata(long orderId) {

		Lot_Order order = lotOrderRepository.findById(orderId);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		Date date = new Date();

		Order_data obj = new Order_data();
		obj.setOrder_date(dateFormat.format(date));
		obj.setTb_id(0000000);
		obj.setChannel_sign("B2BSC");
		obj.setChannel_id(0000000);
		obj.setChannel_no(0000000);
		obj.setPaid(0);
		obj.setApproved(1);
		obj.setItem_count(order.getLot().getVolume());

		double amount = order.getLot().getLotPrice();

		obj.setTotal_item_amount(amount);
		obj.setDate_created(dateFormat.format(date));
		return obj;
	}

	public Sell_To processSellTo(Lot_Order order, long orderId) {

		Sell_To sell_to = new Sell_To();
		sell_to.setTb_id(orderId);
		sell_to.setName(order.getBillingAddress().getContact_person());
		sell_to.setStreet_no(order.getBillingAddress().getStreet());
		sell_to.setZip(order.getBillingAddress().getZip_code());
		sell_to.setCity(order.getBillingAddress().getCity());
		sell_to.setCountry(order.getBillingAddress().getCountry());
		sell_to.setPhone_office(order.getBillingAddress().getPhone_number());
		sell_to.setEmail(order.getUser().getEmail());
		return sell_to;
	}

	public Ship_To processShipTo(Lot_Order order, long orderId) {

		Ship_To ship_To = new Ship_To();
		ship_To.setTb_id(orderId);
		ship_To.setName(order.getShippingAddress().getContact_person());
		ship_To.setStreet_no(order.getShippingAddress().getStreet());
		ship_To.setZip(order.getShippingAddress().getZip_code());
		ship_To.setCity(order.getShippingAddress().getCity());
		ship_To.setCountry(order.getShippingAddress().getCountry());
		ship_To.setPhone_office(order.getShippingAddress().getPhone_number());
		ship_To.setEmail(order.getUser().getEmail());
		return ship_To;

	}

	public Payment paymentProcess() {

		Payment payment = new Payment();
		payment.setType("B2BSC");
		payment.setCosts(0.00);
		payment.setDirectdebit(null);
		return payment;
	}

	public Shipment shipmentProcess() {

		Shipment shipment = new Shipment();
		shipment.setPrice(0.00);
		return shipment;
	}

	public List<Item> processItemList(long orderId) {

		Lot_Order order_o = lotOrderRepository.findById(orderId);
		long lotId = order_o.getLot().getLotId();
		List<Lot_Lager> lager = lot_LagerRepository.findAllByLotId(lotId);
		List<Item> itemList = new ArrayList<Item>();
		for (Lot_Lager lot_Lager : lager) {
			Item item = new Item();
			item.setTb_id(0000L);
			item.setChanel_id(0000L);
			item.setEan(lot_Lager.getEAN());
			item.setSku(lot_Lager.getEAN());
			item.setQuantity(lot_Lager.getBESTAND());
			item.setBilling_text(lot_Lager.getPROD_NAME());
			item.setItem_price(lot_Lager.getPREIS());
			itemList.add(item);

		}
		return itemList;

	}

	public Items processItems(List<Item> item_list) {

		Items items = new Items();
		items.setItem(item_list);
		return items;
	}

	public Order processOrder(Order_data order_data, Sell_To sell_To, Ship_To ship_To, Shipment shipment, Items items,
			Payment payment) {
		Order order = new Order();
		order.setOrder_data(order_data);
		order.setSell_To(sell_To);
		order.setShip_To(ship_To);
		order.setShipment(shipment);
		order.setPayment(payment);
		order.setItems(items);

		return order;
	}

	public String create_order_xml(long orderId) throws IOException {
		Lot_Order order_obj = lotOrderRepository.findById(orderId);

		Sell_To sell_To = processSellTo(order_obj, orderId);
		Ship_To ship_To = processShipTo(order_obj, orderId);
		List<Item> list = processItemList(orderId);
		Items items = processItems(list);
		Shipment shipment = shipmentProcess();
		Payment payment = paymentProcess();
		Order_data order_data = processOrderdata(orderId);
		Order order = processOrder(order_data, sell_To, ship_To, shipment, items, payment);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(order, new File("C://Users//arahim//Desktop//xmlFile//" + "order.xml"));
			marshaller.marshal(order, System.out);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Order XML has been created";
	}

	public String send_XML_to_SFTP(long orderId) throws IOException {

		Lot_Order order_obj = lotOrderRepository.findById(orderId);
		Sell_To sell_To = processSellTo(order_obj, orderId);
		Ship_To ship_To = processShipTo(order_obj, orderId);
		List<Item> list = processItemList(orderId);
		Items items = processItems(list);
		Shipment shipment = shipmentProcess();
		Payment payment = paymentProcess();
		Order_data order_data = processOrderdata(orderId);
		Order order = processOrder(order_data, sell_To, ship_To, shipment, items, payment);

		String server = "ftp.motion-fashion.de";
		int port = 21;
		String ftp_user = "mfne_live5";
		String pass = "oYt73uKE";

		String remote_directory = "htdocs/b2border/IN/";
		FTPClient ftpClient = new FTPClient();
		FTPClientConfig conf = new FTPClientConfig();
		conf.setServerTimeZoneId("UTC");
		ftpClient.configure(conf);

		try {
			ftpClient.connect(server, port);
			ftpClient.login(ftp_user, pass);

			JAXBContext jaxbContext = JAXBContext.newInstance(Order.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// File targetFile = new File(System.getProperty("java.io.tmpdir") + "/" +
			// System.currentTimeMillis() + file.getOriginalFilename());
			// file.transferTo(targetFile);
			String temp_name = "order";
			String temp_o_id = Long.toString(orderId);
			String file_type = ".xml";
			String filename = temp_name + temp_o_id + file_type;
			File targetFile = new File(System.getProperty("java.io.tmpdir") + "/" + filename);

			// File file = new File("order.xml");
			File file = targetFile;
			marshaller.marshal(order, targetFile);
			Random rand = new Random();
			int number = rand.nextInt(50);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			Date date = new Date();

			String file_name = "order_";
			String rand_string = dateFormat.format(date);
			String file_extension = ".xml";
			String firstRemoteFile = remote_directory + file_name + rand_string + file_extension;
			InputStream inputStream = new FileInputStream(targetFile);
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Order XML file has been created!!!";
	}

}// end of LotOrderService
