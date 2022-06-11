package team.arcticfox.frms.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public final class ShoppingItem implements IJsonTextable {
    @JSONField(name = "id")
    public int id;
    @JSONField(name = "medicine-name", ordinal = 1)
    public String name;
    @JSONField(name = "manufacturer", ordinal = 2)
    public String manufacturer;
    @JSONField(name = "specification", ordinal = 3)
    public String specification;
    @JSONField(name = "price", ordinal = 4)
    public double price;
    @JSONField(name = "amount", ordinal = 5)
    public int amount;


    public ShoppingItem() {
        this(0, null, null, null, 0, 0);
    }

    public ShoppingItem(int id, String name, String manufacturer, String specification, double price, int amount) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.specification = specification;
        this.price = price;
        this.amount = amount;
    }


    public static ShoppingItem parse(String json) {
        return JSON.parseObject(json, ShoppingItem.class);
    }


    public Object[] toObjectList() {
        return new Object[]{id, name, amount, price * amount};
    }

    @Override
    public JSONObject toJsonObject() {
        return JSON.parseObject(toJsonString());
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this, true);
    }
}
