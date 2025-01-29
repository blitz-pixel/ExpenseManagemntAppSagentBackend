import React, { useState } from "react";

const RevenuePage = () => {
    const [revenues, setRevenues] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [newRevenue, setNewRevenue] = useState({
        name: "",
        category: "",
        subCategory: "",
        amount: "",
        date: "",
    });

    // Handle input change inside modal
    const handleChange = (field, value) => {
        setNewRevenue({ ...newRevenue, [field]: value });
    };

    // Handle adding the new revenue
    const handleAddRevenue = () => {
        setRevenues([...revenues, { id: revenues.length + 1, serialNo: revenues.length + 1, ...newRevenue }]);
        setShowModal(false); // Close modal after adding
        setNewRevenue({ name: "", category: "", subCategory: "", amount: "", date: "" }); // Reset form
    };

    return (
        <div style={{ padding: "20px", maxWidth: "900px", margin: "auto" }}>
            {/* Header Section with Proper Alignment */}
            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
                <h2 style={{ margin: "0", fontSize: "24px" }}>Revenue Tracker</h2>
                <button
                    onClick={() => setShowModal(true)}
                    style={{
                        padding: "8px 15px",
                        fontSize: "16px",
                        cursor: "pointer",
                    }}
                >
                    Add Revenue
                </button>
            </div>

            {/* Revenue Table */}
            {revenues.length > 0 && (
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
                    {revenues.map((revenue) => (
                        <tr key={revenue.id}>
                            <td>{revenue.serialNo}</td>
                            <td>{revenue.name}</td>
                            <td>{revenue.category}</td>
                            <td>{revenue.subCategory}</td>
                            <td>{revenue.amount}</td>
                            <td>{revenue.date}</td>
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
                        <h3 style={{ marginBottom: "15px" }}>Add Revenue</h3>
                        <label>Name: <input type="text" value={newRevenue.name} onChange={(e) => handleChange("name", e.target.value)} /></label><br /><br />
                        <label>Category: <input type="text" value={newRevenue.category} onChange={(e) => handleChange("category", e.target.value)} /></label><br /><br />
                        <label>Sub-Category: <input type="text" value={newRevenue.subCategory} onChange={(e) => handleChange("subCategory", e.target.value)} /></label><br /><br />
                        <label>Amount: <input type="number" value={newRevenue.amount} onChange={(e) => handleChange("amount", e.target.value)} /></label><br /><br />
                        <label>Date: <input type="date" value={newRevenue.date} onChange={(e) => handleChange("date", e.target.value)} /></label><br /><br />

                        <button onClick={handleAddRevenue} style={{ marginRight: "10px", padding: "5px 10px" }}>Save</button>
                        <button onClick={() => setShowModal(false)} style={{ padding: "5px 10px" }}>Cancel</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default RevenuePage;
