package model;

public class Cart {
	private Integer orderID;
	private String membernumber;
	private String username;
	private String ordernumber;
    private Integer gameA;
    private Integer gameB;
    private Integer gameC;
    private String membership;
    private Double sum;
    private String date;	//希望送達之日期時間

    public Cart() {
        super();
    }

    public Cart(Integer gameA, Integer gameB, Integer gameC, String membership) {
        super();
        this.gameA = gameA;
        this.gameB = gameB;
        this.gameC = gameC;
        this.membership = membership;
        this.sum = this.gameA * 109. + this.gameB * 129 + this.gameC * 159;
    }
	public Cart(String membernumber, String username, String ordernumber, Integer gameA, Integer gameB, Integer gameC,
			String membership, Double sum, String date) {
		super();
		this.membernumber = membernumber;
		this.username = username;
		this.ordernumber = ordernumber;
		this.gameA = gameA;
		this.gameB = gameB;
		this.gameC = gameC;
		this.membership = membership;
		this.sum = sum;
		this.date = date;
	}
	
	public Double calculateDiscountedPrice(String membership) { 
		if (sum == null) {
            sum = this.gameA * 109. + this.gameB * 129. + this.gameC * 159.; // 避免 sum 为 null 时的计算错误
        }
        
        Double discountedPrice = sum;

        if (sum >= 1000) {
            discountedPrice *= 0.9;
        }

        if (membership != null) {
            switch (membership) {
                case "1":
                    discountedPrice *= 0.5;
                    break;
                case "2":
                    discountedPrice *= 0.7;
                    break;
                case "3":
                    discountedPrice *= 0.9;
                    break;
                default:
                    // 如果 membership 是其他值，使用默认折扣
                    break;
            }
        }

        discountedPrice = (double)Math.round(discountedPrice);
        return discountedPrice;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getMembernumber() {
		return membernumber;
	}

	public void setMembernumber(String membernumber) {
		this.membernumber = membernumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public Integer getGameA() {
		return gameA;
	}

	public void setGameA(Integer gameA) {
		this.gameA = gameA;
	}

	public Integer getGameB() {
		return gameB;
	}

	public void setGameB(Integer gameB) {
		this.gameB = gameB;
	}

	public Integer getGameC() {
		return gameC;
	}

	public void setGameC(Integer gameC) {
		this.gameC = gameC;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public Double getSum() {
		return this.getGameA() * 109. + this.getGameB() * 129 + this.gameC * 159;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
   
}
