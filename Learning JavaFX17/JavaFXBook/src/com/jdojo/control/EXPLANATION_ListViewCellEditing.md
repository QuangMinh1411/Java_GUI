# Explanation: ListView cell editing examples

This repository contains two small JavaFX applications that demonstrate how to use ListView cell factories to enable in-place editing with different UI controls.

The examples are:
- ListViewCheckBoxEditing.java — uses CheckBoxListCell to show a checkbox for each list row and bind it to a BooleanProperty.
- ListViewChoiceBoxEditing.java — uses ChoiceBoxListCell so that double-clicking a row turns it into a ChoiceBox, allowing the user to select from predefined items.

## 1) ListViewCheckBoxEditing.java

High-level idea: Each list item (Apple, Banana, Donut, Hash Brown) is displayed with a checkbox. Checking/unchecking a box toggles a BooleanProperty stored in a backing map. A button prints all items that are currently checked.

Key points in the code:
- Data model: A LinkedHashMap<String, BooleanProperty> map keeps insertion order for the ListView and stores the checked state for each item.
  - Keys are the item labels ("Apple", "Banana", ...).
  - Values are BooleanProperty objects (SimpleBooleanProperty) representing checked state.
- UI setup:
  - ListView<String> breakfasts is sized and set to editable = true (editing is relevant for cell factories).
  - SelectionMode.MULTIPLE is enabled (independent of the checkbox state; selection is the highlighted row selection, while the checkbox state is your data model flag).
  - breakfasts.getItems().setAll(map.keySet()) populates the list with the item labels in the same order as the map.
- Cell factory:
  - breakfasts.setCellFactory(CheckBoxListCell.forListView(item -> map.get(item)))
  - forListView expects a callback mapping an item to an ObservableValue<Boolean>. Here we return the BooleanProperty from the map. The CheckBoxListCell will observe this property and reflect/toggle its value as the user checks/unchecks.
- Printing selection:
  - The Print Selection button calls printSelection(), which iterates over the map entries and prints only those whose BooleanProperty is true.

User flow:
1) The window shows a list of breakfast items with checkboxes.
2) The user checks some items.
3) Clicking "Print Selection" prints the names of the checked items to the console.

Why LinkedHashMap?
- It preserves insertion order, so the ListView displays items in the intended order. A plain HashMap would not guarantee order.

## 2) ListViewChoiceBoxEditing.java

High-level idea: The ListView initially contains four placeholder strings ("[Double click to select]"). When the user double-clicks a row, that row becomes a ChoiceBox listing the available breakfast items (Apple, Banana, Donut, Hash Brown). Choosing an item replaces the placeholder text in the row with the chosen value.

Key points in the code:
- Data model: The ListView is directly backed by an ObservableList<String> (implicit via breakfasts.getItems()). There’s no custom object type; each cell holds a string.
- Placeholders:
  - A loop adds four placeholders, effectively allowing the user to make up to four choices at different rows.
- Choice options:
  - ObservableList<String> items = FXCollections.observableArrayList("Apple", "Banana", "Donut", "Hash Brown") holds the selectable values.
- Cell factory:
  - breakfasts.setCellFactory(ChoiceBoxListCell.forListView(items))
  - This equips each cell with editing behavior: entering edit mode (e.g., double-click) transforms the cell into a ChoiceBox populated with the provided items. Committing the edit writes the chosen value back to the ListView’s items list so the cell displays that value thereafter.
- UI layout: A VBox stacks informative labels and the ListView with some padding and border styling.

User flow:
1) The list shows four rows labeled "[Double click to select]".
2) The user double-clicks a row; a ChoiceBox appears with breakfast options.
3) The user selects an option; the row now shows the selected item.

## Related concepts
- Editable ListView: setEditable(true) enables cells to switch into an editor control when they support it (e.g., ChoiceBoxListCell). It’s not strictly required for CheckBoxListCell to reflect checked state, but is commonly enabled for consistency with editable behavior.
- Selection vs. editing state: Selection (SelectionModel) is which rows are highlighted; editing is when a cell enters an input mode (like a ChoiceBox) or exposes an interactive control. In the checkbox example, the checkbox state is bound to a BooleanProperty regardless of selection.
- Cell factories: Provide custom rendering/editing logic by returning cells that know how to display and edit the underlying item.

## Summary
- ListViewCheckBoxEditing binds each item to a BooleanProperty with CheckBoxListCell, enabling persistent checked state and an easy way to query it.
- ListViewChoiceBoxEditing turns each row into a ChoiceBox on edit, letting users pick from fixed options and storing the chosen string directly in the list’s items.

These examples demonstrate two common ListView customization patterns in JavaFX: boolean toggling per row and choice-based editing per row.