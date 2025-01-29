import React from "react";
import { Link } from "react-router-dom";
import { Button, Typography, Container, Box } from "@mui/material";

const Home = () => {
    return (
        <Container maxWidth="sm">
            <Box sx={{ textAlign: "center", mt: 5 }}>
                <Typography variant="h3" component="h1" gutterBottom>
                    Home Page
                </Typography>
                <Box sx={{ display: "flex", justifyContent: "center", gap: 2 }}>
                    <Link to="/Registration" style={{ textDecoration: "none" }}>
                        <Button variant="contained" color="primary">
                            Register
                        </Button>
                    </Link>
                    <Link to="/Login" style={{ textDecoration: "none" }}>
                        <Button variant="outlined" color="secondary">
                            Login
                        </Button>
                    </Link>
                </Box>
            </Box>
        </Container>
    );
};

export default Home;
