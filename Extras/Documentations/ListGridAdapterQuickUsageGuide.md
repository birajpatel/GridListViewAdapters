Defining POJO Classes
===

```java

class CardData {
	// Class to hold data for single card.
}

class CardViewHolder {
	// To hold views present in card.
}

```
Linking your POJO to Adapter is as simplest as shown below.
==

```java
class MySimpleGridAdapter extends ListGridAdapter<CardData, CardViewHolder> {

	public MySimpleGridAdapter(Context context, int totalCardsInRow) {
		super(context, totalCardsInRow);
	}

	@Override
	protected Card<CardViewHolder> getNewCard(int cardwidth) {
		// 1.Create Card-View programmatically (can be created by XML as well.)
		// 2.Setting up Card view holder.
		return new Card<CardViewHolder>(view, viewHolder);
	}

	@Override
	protected void setCardView(CardDataHolder<CardData> cardDataHolder,
			CardViewHolder cardViewHolder) {
		// Update view values using your data & viewHolder.
	}

	@Override
	protected void onCardClicked(CardData cardData) {
		// Callback when card gets clicked.
	}

	@Override
	protected void registerChildrenViewClickEvents(
			CardViewHolder cardViewHolder,
			ChildViewsClickHandler childViewsClickHandler) {
		// Register for child-view (present inside your card) clicks if required
	}

	@Override
	protected void onChildViewClicked(View clickedChildView, CardData cardData,
			int eventId) {
		// Click events for registerd chilren will be posted here
	}

}
```
Linking MySimpleGridAdapter to ListView
====

```java
// Instantiating adapter
final int MAX_CARDS = 2;
MySimpleGridAdapter gridAdapter = new MySimpleGridAdapter(getApplicationContext(), MAX_CARDS);

// Adding data to adapter
gridAdapter.addItemsInGrid(dataList);
		
// Attaching adapter to ListView	
listview.setAdapter(gridAdapter);

```


Done
====
