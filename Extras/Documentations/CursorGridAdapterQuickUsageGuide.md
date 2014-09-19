Defining View Holder Class
===

```java

class CardViewHolder {
	// To hold views present in card.
}

```
Linking your Cursor to Adapter is as simplest as shown below.
==

```java
class MyCursorGridAdapter extends CursorGridAdapter<CardViewHolder> {

	public MyCursorGridAdapter(Context context, int totalCardsInRow, Cursor c) {
		super(context, totalCardsInRow, c);
	}

	@Override
	protected Card<CardViewHolder> getNewCard(int cardwidth) {
		// 1.Create Card-View programmatically (can be created by XML as well.)
		// 2.Setting up Card view holder.
		return new Card<CardViewHolder>(view, viewHolder);
	}

	@Override
	protected void setCardView(CardDataHolder<Cursor> cardDataHolder,
			CardViewHolder cardViewHolder) {
		// Update view values using your data & viewHolder.
	}

	@Override
	protected void onCardClicked(Cursor cardData) {
		// Callback when card gets clicked.
	}

	@Override
	protected void registerChildrenViewClickEvents(
			CardViewHolder cardViewHolder,
			ChildViewsClickHandler childViewsClickHandler) {
		// Register for child-view (present inside your card) clicks if required
	}

	@Override
	protected void onChildViewClicked(View clickedChildView, Cursor cardData,
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
MyCursorGridAdapter gridAdapter = new MyCursorGridAdapter(getApplicationContext(), MAX_CARDS,null);
		
// Attaching adapter to ListView	
listview.setAdapter(gridAdapter);

// Adding data to adapter on callback from CursorLoader.
    @Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor data) {
		gridAdapter.swapCursor(data);
	}

```


Done
====
