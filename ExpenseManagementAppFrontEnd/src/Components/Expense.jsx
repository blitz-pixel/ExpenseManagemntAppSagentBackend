import React, { useState } from "react";

const ExpensePage = () => {
    const [expenses, setExpenses] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newExpense, setNewExpense] = useState({
        name: "",
        category: "",
        subCategory: "",
        amount: "",
        date: "",
    });

    // Handle input change inside modal
    const handleChange = (field, value) => {
        setNewExpense({ ...newExpense, [field]: value });
    };

    // Handle adding the new expense
    const handleAddExpense = () => {
        setExpenses([...expenses, { id: expenses.length + 1, serialNo: expenses.length + 1, ...newExpense }]);
        setShowModal(false); // Close modal after adding
        setNewExpense({ name: "", category: "", subCategory: "", amount: "", date: "" }); // Reset form
    };

    return (
        <div style={{ padding: "20px", maxWidth: "900px", margin: "auto" }}>
            {/* Header Section with Proper Alignment */}
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                <h2 style={{ margin: "0", fontSize: "24px" }}>Expense Tracker</h2>
                <button
                    onClick={() => setShowModal(true)}
                    style={{
                        padding: "8px 15px",
                        fontSize: "16px",
                        cursor: "pointer",
                    }}
                >
                    Add Expense
                </button>
            </div>

            {/* Expense Table */}
            {expenses.length > 0 && (
                <table border="1" cellPadding="10" style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
                    <thead>
                    <tr>
                        <th>S.No</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Sub-Category</th>
                        <th>Amount</th>
                        <th>Date Added</th>
                    </tr>
                    </thead>
                    <tbody>
                    {expenses.map((expense) => (
                        <tr key={expense.id}>
                            <td>{expense.serialNo}</td>
                            <td>{expense.name}</td>
                            <td>{expense.category}</td>
                            <td>{expense.subCategory}</td>
                            <td>{expense.amount}</td>
                            <td>{expense.date}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}

            {/* Popup Modal */}
            {showModal && (
                <div style={{
                    position: "fixed", top: "0", left: "0", width: "100%", height: "100%",
                    backgroundColor: "rgba(0, 0, 0, 0.5)", display: "flex", justifyContent: "center", alignItems: "center"
                }}>
                    <div style={{
                        background: "#fff", padding: "20px", borderRadius: "8px", width: "350px",
                        boxShadow: "0px 0px 10px rgba(0,0,0,0.2)"
                    }}>
                        <h3 style={{ marginBottom: "15px" }}>Add Expense</h3>
                        <label>Name: <input type="text" value={newExpense.name} onChange={(e) => handleChange("name", e.target.value)} /></label><br /><br />
                        <label>Category: <input type="text" value={newExpense.category} onChange={(e) => handleChange("category", e.target.value)} /></label><br /><br />
                        <label>Sub-Category: <input type="text" value={newExpense.subCategory} onChange={(e) => handleChange("subCategory", e.target.value)} /></label><br /><br />
                        <label>Amount: <input type="number" value={newExpense.amount} onChange={(e) => handleChange("amount", e.target.value)} /></label><br /><br />
                        <label>Date: <input type="date" value={newExpense.date} onChange={(e) => handleChange("date", e.target.value)} /></label><br /><br />

                        <button onClick={handleAddExpense} style={{ marginRight: "10px", padding: "5px 10px" }}>Save</button>
                        <button onClick={() => setShowModal(false)} style={{ padding: "5px 10px" }}>Cancel</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ExpensePage;
