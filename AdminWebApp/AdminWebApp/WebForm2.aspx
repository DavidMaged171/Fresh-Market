<%@ Page Title="" Language="C#" MasterPageFile="~/master.Master" AutoEventWireup="true" CodeBehind="WebForm2.aspx.cs" Inherits="AdminWebApp.WebForm2" %>
<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <p>
    <br />
</p>
<p style="font-weight: 700; font-size: x-large">
    Users Page Control</p>
<p style="font-weight: 700; font-size: x-large; text-align: left">
    <asp:GridView ID="GridView1" runat="server" AllowPaging="True" AllowSorting="True" AutoGenerateColumns="False" CellPadding="4" DataKeyNames="UserID" DataSourceID="SqlDataSource1" ForeColor="#333333" GridLines="None" Width="1141px">
        <AlternatingRowStyle BackColor="White" />
        <Columns>
            <asp:CommandField ButtonType="Button" ShowDeleteButton="True" ShowSelectButton="True" />
            <asp:BoundField DataField="Name" HeaderText="Name" SortExpression="Name" />
            <asp:BoundField DataField="Phone" HeaderText="Phone" SortExpression="Phone" />
            <asp:BoundField DataField="Email" HeaderText="Email" SortExpression="Email" />
            <asp:BoundField DataField="Address" HeaderText="Address" SortExpression="Address" />
            <asp:TemplateField HeaderText="Image">
                <ItemTemplate>
                    <asp:Image ID="Image1" runat="server" Height="87px" ImageUrl='<%# Eval("image") %>' Width="115px" />
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
        <EditRowStyle BackColor="#2461BF" />
        <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
        <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
        <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
        <RowStyle BackColor="#EFF3FB" />
        <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
        <SortedAscendingCellStyle BackColor="#F5F7FB" />
        <SortedAscendingHeaderStyle BackColor="#6D95E1" />
        <SortedDescendingCellStyle BackColor="#E9EBEF" />
        <SortedDescendingHeaderStyle BackColor="#4870BE" />
    </asp:GridView>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:db_a8b358_viadbConnectionString %>" DeleteCommand="DELETE FROM [users] WHERE [UserID] = @UserID" InsertCommand="INSERT INTO [users] ([Name], [Phone], [Email], [Address], [password], [image]) VALUES (@Name, @Phone, @Email, @Address, @password, @image)" SelectCommand="SELECT * FROM [users]" UpdateCommand="UPDATE [users] SET [Name] = @Name, [Phone] = @Phone, [Email] = @Email, [Address] = @Address, [password] = @password, [image] = @image WHERE [UserID] = @UserID">
        <DeleteParameters>
            <asp:Parameter Name="UserID" Type="Int32" />
        </DeleteParameters>
        <InsertParameters>
            <asp:Parameter Name="Name" Type="String" />
            <asp:Parameter Name="Phone" Type="String" />
            <asp:Parameter Name="Email" Type="String" />
            <asp:Parameter Name="Address" Type="String" />
            <asp:Parameter Name="password" Type="String" />
            <asp:Parameter Name="image" Type="String" />
        </InsertParameters>
        <UpdateParameters>
            <asp:Parameter Name="Name" Type="String" />
            <asp:Parameter Name="Phone" Type="String" />
            <asp:Parameter Name="Email" Type="String" />
            <asp:Parameter Name="Address" Type="String" />
            <asp:Parameter Name="password" Type="String" />
            <asp:Parameter Name="image" Type="String" />
            <asp:Parameter Name="UserID" Type="Int32" />
        </UpdateParameters>
    </asp:SqlDataSource>
</p>
</asp:Content>
