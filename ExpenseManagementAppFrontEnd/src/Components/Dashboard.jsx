import { WalletCards, Receipt } from "lucide-react";
import { Link } from "react-router-dom";
import { Box, Grid, Paper, Typography, Button } from "@mui/material";

function Dashboard() {
  return (
    <Box
      sx={{
        display: "grid",
        gridTemplateColumns: { xs: "1fr", md: "1fr 1fr" },
        gap: 3,
        justifyContent: "center",
        padding: 3,
        height: "100%",
      }}
    >
      <Grid container spacing={3}>
        {/* Income Overview Card */}
        <Grid item xs={12} sm={6}>
          <Link to="/income" style={{ textDecoration: "none" }}>
            <Paper
              sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                padding: 2,
                boxShadow: 3,
                borderRadius: 2,
                backgroundColor: "white",
              }}
            >
              <img
                src="/docs/images/blog/image-1.jpg"
                alt="Income Overview"
                style={{
                  width: "100%",
                  height: "auto",
                  borderRadius: "8px",
                  marginBottom: "16px",
                }}
              />
              <Typography variant="h5" sx={{ fontWeight: "bold", marginBottom: 2 }}>
                Income Overview
              </Typography>
              <Typography sx={{ marginBottom: 2 }}>
                Manage and track all your income streams here.
              </Typography>
              <Button variant="contained" color="primary" sx={{ padding: "8px 20px" }}>
                View Details
              </Button>
            </Paper>
          </Link>
        </Grid>

        {/* Expense Overview Card */}
        <Grid item xs={12} sm={6}>
          <Link to="/expense" style={{ textDecoration: "none" }}>
            <Paper
              sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                padding: 2,
                boxShadow: 3,
                borderRadius: 2,
                backgroundColor: "white",
              }}
            >
              <img
                src="/docs/images/blog/image-1.jpg"
                alt="Expense Overview"
                style={{
                  width: "100%",
                  height: "auto",
                  borderRadius: "8px",
                  marginBottom: "16px",
                }}
              />
              <Typography variant="h5" sx={{ fontWeight: "bold", marginBottom: 2 }}>
                Expense Overview
              </Typography>
              <Typography sx={{ marginBottom: 2 }}>
                Keep track of your expenses and manage your budgets.
              </Typography>
              <Button variant="contained" color="primary" sx={{ padding: "8px 20px" }}>
                View Details
              </Button>
            </Paper>
          </Link>
        </Grid>

        {/* Report Card */}
        <Grid item xs={12} sm={6}>
          <Paper
            sx={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              padding: 2,
              boxShadow: 3,
              borderRadius: 2,
              backgroundColor: "white",
            }}
          >
            <img
              src="/docs/images/blog/image-1.jpg"
              alt="ReportImg"
              style={{
                width: "100%",
                height: "auto",
                borderRadius: "8px",
                marginBottom: "16px",
              }}
            />
            <Typography variant="h5" sx={{ fontWeight: "bold", marginBottom: 2 }}>
              Report
            </Typography>
            <Typography sx={{ marginBottom: 2 }}>
              Get transaction report here.
            </Typography>
            <Button variant="contained" color="primary" sx={{ padding: "8px 20px" }}>
              View Details
            </Button>
          </Paper>
        </Grid>

        {/* Recent Transactions Card */}
        <Grid item xs={12} sm={6}>
          <Paper
            sx={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              padding: 2,
              boxShadow: 3,
              borderRadius: 2,
              backgroundColor: "white",
            }}
          >
            <img
              src="/docs/images/blog/image-1.jpg"
              alt="Recent Transactions"
              style={{
                width: "100%",
                height: "auto",
                borderRadius: "8px",
                marginBottom: "16px",
              }}
            />
            <Typography variant="h5" sx={{ fontWeight: "bold", marginBottom: 2 }}>
              Recent Transactions
            </Typography>
            <Typography sx={{ marginBottom: 2 }}>
              View most recent Transactions here.
            </Typography>
            <Button variant="contained" color="primary" sx={{ padding: "8px 20px" }}>
              View Details
            </Button>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
}

export default Dashboard;
