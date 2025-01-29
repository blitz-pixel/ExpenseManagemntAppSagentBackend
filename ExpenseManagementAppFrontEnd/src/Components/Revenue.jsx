import React, { useState } from "react";
import { Button, TextField, Modal, Box, Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from "@mui/material";

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
                <Typography variant="h4" gutterBottom>Revenue Tracker</Typography>
                <Button variant="contained" color="primary" onClick={() => setShowModal(true)}>Add Revenue</Button>
            </div>

            {/* Revenue Table */}
            {revenues.length > 0 && (
                <TableContainer component={Paper} style={{ marginTop: "20px" }}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>S.No</TableCell>
                                <TableCell>Name</TableCell>
                                <TableCell>Category</TableCell>
                                <TableCell>Sub-Category</TableCell>
                                <TableCell>Amount</TableCell>
                                <TableCell>Date Added</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {revenues.map((revenue) => (
                                <TableRow key={revenue.id}>
                                    <TableCell>{revenue.serialNo}</TableCell>
                                    <TableCell>{revenue.name}</TableCell>
                                    <TableCell>{revenue.category}</TableCell>
                                    <TableCell>{revenue.subCategory}</TableCell>
                                    <TableCell>{revenue.amount}</TableCell>
                                    <TableCell>{revenue.date}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            )}

            {/* Popup Modal */}
            <Modal
                open={showModal}
                onClose={() => setShowModal(false)}
                aria-labelledby="add-revenue-modal"
                aria-describedby="form-to-add-new-revenue"
            >
                <Box sx={{ 
                    width: 400, 
                    bgcolor: "white", 
                    borderRadius: "8px", 
                    p: 3, 
                    position: "absolute", 
                    top: "50%", 
                    left: "50%", 
                    transform: "translate(-50%, -50%)", 
                    boxShadow: 24 
                }}>
                    <Typography variant="h6" gutterBottom>Add Revenue</Typography>
                    <TextField
                        label="Name"
                        variant="outlined"
                        fullWidth
                        value={newRevenue.name}
                        onChange={(e) => handleChange("name", e.target.value)}
                        sx={{ mb: 2 }}
                    />
                    <TextField
                        label="Category"
                        variant="outlined"
                        fullWidth
                        value={newRevenue.category}
                        onChange={(e) => handleChange("category", e.target.value)}
                        sx={{ mb: 2 }}
                    />
                    <TextField
                        label="Sub-Category"
                        variant="outlined"
                        fullWidth
                        value={newRevenue.subCategory}
                        onChange={(e) => handleChange("subCategory", e.target.value)}
                        sx={{ mb: 2 }}
                    />
                    <TextField
                        label="Amount"
                        variant="outlined"
                        fullWidth
                        type="number"
                        value={newRevenue.amount}
                        onChange={(e) => handleChange("amount", e.target.value)}
                        sx={{ mb: 2 }}
                    />
                    <TextField
                        label="Date"
                        variant="outlined"
                        fullWidth
                        type="date"
                        value={newRevenue.date}
                        onChange={(e) => handleChange("date", e.target.value)}
                        sx={{ mb: 2 }}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                    <div style={{ display: "flex", justifyContent: "flex-end" }}>
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={handleAddRevenue}
                            sx={{ mr: 1 }}
                        >
                            Save
                        </Button>
                        <Button
                            variant="outlined"
                            color="secondary"
                            onClick={() => setShowModal(false)}
                        >
                            Cancel
                        </Button>
                    </div>
                </Box>
            </Modal>
        </div>
    );
};

export default RevenuePage;
