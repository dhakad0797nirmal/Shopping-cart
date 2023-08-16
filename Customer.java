package shopping;

public class Customer {
	
	private String customerid;
    private String customername;
    private String customercontact;
    private String customeraddress;
    private String customerpassword;
	
	
    public Customer() { }
    
	public Customer(String id,String name,String contact,String address,String password) {
		customerid=id;
		customername=name;
		customercontact=contact;
		customeraddress=address;
		customerpassword=password;
	}
	
	public String getId() {
        return customerid;
        
    }

    public String getPassword() {
        return customerpassword;
    }

	public String getCustomername() {
		return customername;
	}

	public String getCustomercontact() {
		return customercontact;
	}

	public String getCustomeraddress() {
		return customeraddress;
	}
	

}
