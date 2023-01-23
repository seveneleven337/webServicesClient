package client;
import javax.xml.namespace.QName;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
public class CustomerRPCClient {
      
    public static void main(String[] args1) throws AxisFault, Exception {
        RPCServiceClient serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        
        
        // EndpointReference targetEPR = new
        // EndpointReference("http://localhost:8080/axis2/services/CustomerService");
        EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis2/services/assignment1_CustomerService");
        
        
        options.setTo(targetEPR);
        
        int[] ids = { 34000, 35000, 36000 };
        String[] names = { "Siirus", "Sasha", "Lizzie" };
        float[] shoppinGAmounts = { 123.54f, 320.40f, 58.89f };
        boolean[] priviliged = { true, true, false };
        int[] discountPercentages = { 3, 10, 2 };
        
        // Setting the customer information
        QName opSetCustomer = new QName("http://service.customer.ws", "setCustomer");
    
        Object[] opSetCustomerArgs = null;
        
        for (int i = 0; i < ids.length; i++) {
            opSetCustomerArgs = new Object[] { names[i], ids[i], shoppinGAmounts[i], priviliged[i],discountPercentages[i]  };
            // We call invokeRobust() method if we wouldn't expect any response from
            // the server
             serviceClient.invokeRobust(opSetCustomer, opSetCustomerArgs);
            // We call invokeBlocking() method if the we expect a value from the web service
            /*
             * Class[] setCustomerReturnTypes = new Class[] { Integer.class };
              Object[] setCustomerResponse = serviceClient.invokeBlocking(opSetCustomer,
             * opSetCustomerArgs, setCustomerReturnTypes);
             *
             * System.out.println("Number of customers on the server: " + (Integer)
             * setCustomerResponse[0]);
             */
        }
        // Here we get the customer information
        
        
//delete services example        
        /*int id = 36000;
        QName opDeleteCustomer = new QName("http://service.customer.ws", "deleteCustomer");
        Object[] opDeleteCustomerArg = new Object[] { id};
        serviceClient.invokeRobust(opDeleteCustomer, opDeleteCustomerArg);*/
 
//update service example        
        /*int id = 36000;
        QName opUpdateCustomer = new QName("http://service.customer.ws", "updateCustomer");
        //(int id, String name, Float shoppingAmount, Boolean privileged, Integer discountPercentage)
        Object[] opUpdateCustomerArg = new Object[] { id, "mlokitrokiilo", 3, true, 4};
        serviceClient.invokeRobust(opUpdateCustomer, opUpdateCustomerArg);*/
// get service example        
        /*int id = 36000;
        QName opGetCustomer = new QName("http://service.customer.ws", "getCustomer");
        Object[] opGetCustomerArgs = new Object[] { id };
        
        
        @SuppressWarnings("unchecked")
        Class<String>[] returnTypes = new Class[] { String.class };
        Object[] response = serviceClient.invokeBlocking(opGetCustomer, opGetCustomerArgs, returnTypes);
        String result =  response[0].toString();
        
        // Here we displaying the result
        System.out.println(result);*/
        
        System.out.println("----------------------------");
 
        
        QName opCustomerInterval = new QName("http://service.customer.ws", "getCustomerInterval");
        Object[] opCustomerIntervalArg = new Object[] { 300, 500 };
        
        
        @SuppressWarnings("unchecked")
        Class[] returnTypes = new Class[] { Customer[].class };
        Object[] response = serviceClient.invokeBlocking(opCustomerInterval, opCustomerIntervalArg, returnTypes);
        Customer[] result = (Customer[]) response[0];
        

        for (int i = 0; i < result.length; i++) {
        	if(result[i] != null) {
                System.out.println(result[i].getCustomerName() + " " + result[i].getShoppingAmount());
        	}
        }
    }
}